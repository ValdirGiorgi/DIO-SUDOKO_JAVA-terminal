package br.com.dio.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import static java.util.Objects.nonNull;
import java.util.Set;

public class Board {

    private final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }
    
    public GameStatusEnum getStatus() {
        boolean hasAnyValue = false;
        boolean allFilled = true;
        
        // Percorre todas as posições do tabuleiro
        for (List<Space> row : spaces) {
            for (Space space : row) {
                // Verificamos se alguma posição não-fixa tem valor (jogo iniciado)
                if (!space.isFixed() && nonNull(space.getActual())) {
                    hasAnyValue = true;
                }
                
                // Verificamos se alguma posição não-fixa está vazia (jogo incompleto)
                if (!space.isFixed() && (space.getActual() == null || space.getActual() == 0)) {
                    allFilled = false;
                }
            }
        }
        
        // Se não tem nenhum valor preenchido pelo usuário, jogo não iniciado
        if (!hasAnyValue) {
            return GameStatusEnum.NON_STARTED;
        }
        
        // Se todas as posições estão preenchidas, jogo completo
        if (allFilled) {
            return GameStatusEnum.COMPLETED;
        }
        
        // Caso contrário, jogo incompleto
        return GameStatusEnum.INCOMPLETE;
    }

    public boolean hasError() {
        // Verifica linhas
        for (int row = 0; row < 9; row++) {
            if (hasErrorInRow(row)) {
                return true;
            }
        }
        
        // Verifica colunas
        for (int col = 0; col < 9; col++) {
            if (hasErrorInColumn(col)) {
                return true;
            }
        }
        
        // Verifica os 9 blocos de 3x3
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                if (hasErrorInBlock(blockRow, blockCol)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean hasErrorInRow(int row) {
        Set<Integer> numbers = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            Integer value = getValueAt(row, col);
            if (value != null && value > 0) {
                if (numbers.contains(value)) {
                    return true; // Encontrou duplicata na linha
                }
                numbers.add(value);
            }
        }
        return false;
    }

    private boolean hasErrorInColumn(int col) {
        Set<Integer> numbers = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            Integer value = getValueAt(row, col);
            if (value != null && value > 0) {
                if (numbers.contains(value)) {
                    return true; // Encontrou duplicata na coluna
                }
                numbers.add(value);
            }
        }
        return false;
    }

    private boolean hasErrorInBlock(int blockRow, int blockCol) {
        Set<Integer> numbers = new HashSet<>();
        for (int row = blockRow * 3; row < blockRow * 3 + 3; row++) {
            for (int col = blockCol * 3; col < blockCol * 3 + 3; col++) {
                Integer value = getValueAt(row, col);
                if (value != null && value > 0) {
                    if (numbers.contains(value)) {
                        return true; // Encontrou duplicata no bloco
                    }
                    numbers.add(value);
                }
            }
        }
        return false;
    }

    // Método auxiliar para obter o valor da posição (atual ou esperado)
    private Integer getValueAt(int row, int col) {
        Space space = spaces.get(row).get(col);
        if (space.isFixed()) {
            return space.getExpected();
        } else {
            return space.getActual();
        }
    }

    public boolean changeValue(int row, int column, int value) {
        if (getStatus() == GameStatusEnum.COMPLETED) {
            return false;
        }

        Space space = spaces.get(row).get(column);
        if (space.isFixed()) {
            return false;
        }

        space.setActual(value);
        return true;
    }

    public boolean clearValue(int row, int column) {
        if (getStatus() == GameStatusEnum.COMPLETED) {
            return false;
        }

        Space space = spaces.get(row).get(column);
        if (space.isFixed()) {
            return false;
        }

        space.clearSpace();
        return true;
    }

    public boolean reset(){
        if (getStatus() == GameStatusEnum.COMPLETED) {
            return false;
        }

        spaces.stream().flatMap(Collection::stream).forEach(Space::clearSpace);
        return true;
    }

    public boolean gameIsFinished() {
        return !hasError() && getStatus() == GameStatusEnum.COMPLETED;
    }


}



