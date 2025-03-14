import br.com.dio.model.Board;
import br.com.dio.model.Space;
import br.com.dio.util.BoardTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int  BOARD_LIMIT = 9;

    public static void main(String[] args) {
        
        final Map<String, String> positions  = Map.ofEntries(
            // Original fixed positions (unchanged)

            Map.entry("0,2", "4,true"),
            Map.entry("0,3", "6,false"),
            Map.entry("0,4", "7,true"),
            Map.entry("0,5", "8,false"),
            Map.entry("0,6", "9,false"),
            Map.entry("0,7", "1,false"),
            Map.entry("0,8", "2,false"),
            
            Map.entry("1,0", "6,true"),
            Map.entry("1,1", "7,false"),
            Map.entry("1,2", "2,false"),
            Map.entry("1,3", "1,true"),
            Map.entry("1,4", "9,true"),
            Map.entry("1,5", "5,true"),
            Map.entry("1,6", "3,false"),
            Map.entry("1,7", "4,false"),
            Map.entry("1,8", "8,false"),
            
            Map.entry("2,0", "1,false"),
            Map.entry("2,1", "9,true"),
            Map.entry("2,2", "8,true"),
            Map.entry("2,3", "3,false"),
            Map.entry("2,4", "4,false"),
            Map.entry("2,5", "2,false"),
            Map.entry("2,6", "5,false"),
            Map.entry("2,7", "6,true"),
            Map.entry("2,8", "7,false"),
            
            Map.entry("3,0", "8,true"),
            Map.entry("3,1", "5,false"),
            Map.entry("3,2", "9,false"),
            Map.entry("3,3", "7,false"),
            Map.entry("3,4", "6,true"),
            Map.entry("3,5", "1,false"),
            Map.entry("3,6", "4,false"),
            Map.entry("3,7", "2,false"),
            Map.entry("3,8", "3,true"),
            
            Map.entry("4,0", "4,true"),
            Map.entry("4,1", "2,false"),
            Map.entry("4,2", "6,false"),
            Map.entry("4,3", "8,true"),
            Map.entry("4,4", "5,false"),
            Map.entry("4,5", "3,true"),
            Map.entry("4,6", "7,false"),
            Map.entry("4,7", "9,false"),
            Map.entry("4,8", "1,true"),
            
            Map.entry("5,0", "7,true"),
            Map.entry("5,1", "1,false"),
            Map.entry("5,2", "3,false"),
            Map.entry("5,3", "9,false"),
            Map.entry("5,4", "2,true"),
            Map.entry("5,5", "4,false"),
            Map.entry("5,6", "8,false"),
            Map.entry("5,7", "5,false"),
            Map.entry("5,8", "6,true"),
            
            Map.entry("6,0", "9,false"),
            Map.entry("6,1", "6,true"),
            Map.entry("6,2", "1,false"),
            Map.entry("6,3", "5,false"),
            Map.entry("6,4", "3,false"),
            Map.entry("6,5", "7,false"),
            Map.entry("6,6", "2,true"),
            Map.entry("6,7", "8,true"),
            Map.entry("6,8", "4,false"),
            
            Map.entry("7,0", "2,false"),
            Map.entry("7,1", "8,false"),
            Map.entry("7,2", "7,false"),
            Map.entry("7,3", "4,true"),
            Map.entry("7,4", "1,true"),
            Map.entry("7,5", "9,true"),
            Map.entry("7,6", "6,false"),
            Map.entry("7,7", "3,false"),
            Map.entry("7,8", "5,true"),
            
            Map.entry("8,0", "3,false"),
            Map.entry("8,1", "4,false"),
            Map.entry("8,2", "5,false"),
            Map.entry("8,3", "2,false"),
            Map.entry("8,4", "8,true"),
            Map.entry("8,5", "6,false"),
            Map.entry("8,6", "1,false"),
            Map.entry("8,7", "7,true"),
            Map.entry("8,8", "9,true")
        );

        var option = 1;
        while (true) { 
            System.out.println("Selecione uma das opções abaixo: ");
            System.out.println("1  - Iniciar o jogo");
            System.out.println("2  - Colocar valor");
            System.out.println("3  - Remover valor");
            System.out.println("4  - Visualizar o jogo");
            System.out.println("5  - Verificar status do jogo");
            System.out.println("6  - limpar o jogo");
            System.out.println("7  - Finalizar o jogo");
            System.out.println("8  - Sair");

            option = scanner.nextInt();
            
            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida");
                
                    
                }
            }
}

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("Jogo não iniciado");
            return;
        }
       if (board.gameIsFinished()) {
            System.out.println("Parabéns, você concluiu o jogo");
            showCurrentGame();
        } else if (board.hasError()) {
            System.out.println("O jogo tem erros");
        } else {
            System.out.println("Você ainda precisa preencher algumas posições");
        }
        
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("Jogo já iniciado");
            return;
        }
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                String key = i + "," + j;
                String positionsConfig = positions.get(key);
                if (positionsConfig != null) {
                    var expected = Integer.parseInt(positionsConfig.split(",")[0]);
                    var fixed = Boolean.parseBoolean(positionsConfig.split(",")[1]);
                    var correntSpace = new Space(expected, fixed);
                    // Se o espaço tem um valor esperado mas não é fixo, inicializamos o valor atual com o esperado
                    if (!fixed && expected > 0) {
                        correntSpace.setActual(expected);
                    }
                    spaces.get(i).add(correntSpace);
                } else {
                    spaces.get(i).add(new Space(0, false));
                }
            }
        }
        board = new Board(spaces);
        System.out.println("Jogo está pronto para iniciar");
        showCurrentGame();
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("Jogo não iniciado");
            return;
        }
        System.out.println("Digite a Coluna: ");
        var col = runUntilValidNumber(0,8);
        System.out.println("Digite a Linha: ");
        var row = runUntilValidNumber(0,8);
        System.out.println("informe o numero que vai entrar na posição [%s,%s]: ".formatted(row, col));
        var value = runUntilValidNumber(1,9);
        if (!board.changeValue(row, col, value)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", row, col);
        }

    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("Jogo não iniciado");
            return;
        }
        System.out.println("Digite a Coluna: ");
        var col = runUntilValidNumber(0,8);
        System.out.println("Digite a Linha: ");
        var row = runUntilValidNumber(0,8);
        if (!board.clearValue(row, col)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", row, col);
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("Jogo não iniciado");
            return;
        }
        var args = new Object[81];
        var argPos = 0;
        
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var space = board.getSpaces().get(i).get(j);
                Integer value;
                if (space.isFixed()) {
                    value = space.getExpected();
                } else {
                    value = space.getActual();
                }
                args[argPos++] = (value == null || value == 0) ? " " : value;
            }
        }
        
        System.out.println("Seu jogo se encontra da seguinte forma: ");
        System.out.printf((BoardTemplate.BOARD_TEMPLATE) + "%n", args);    
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("Jogo não iniciado");
            return;
        }
        System.out.println("O status do jogo é: %s".formatted(board.getStatus().getLabel()));
        if (board.hasError()) {
            System.out.println("O jogo tem erros");
        } else {
            System.out.println("O jogo não tem erros");
        }
        
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("Jogo não iniciado");
            return;
        }
        System.out.println("Você tem certeza que deseja limpar o jogo? (s/n)");
        var option = scanner.next();
        if (option.equalsIgnoreCase("sim")) {
            board.reset();
            System.out.println("Jogo limpo com sucesso");
        } else {
            System.out.println("Jogo não foi limpo");
        }
    }

    private static  int runUntilValidNumber(int min, int max) {
        var current = scanner.nextInt();
        
        while (current < min || current > max) {
            System.out.println("Informe um número entre %s e %s".formatted(min, max));
            current = scanner.nextInt();
        }
        return current;
    }

}
