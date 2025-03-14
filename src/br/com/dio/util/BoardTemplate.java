package br.com.dio.util;

public class BoardTemplate {
    
    /**
     * Template para o tabuleiro de Sudoku
     * Representação visual de um tabuleiro 9x9 vazio com divisões para os quadrantes
     * Inclui identificadores numéricos para linhas e colunas
     * Contagem iniciando de 0
     */
    public static final String BOARD_TEMPLATE = 
            "    0 1 2   3 4 5   6 7 8  \n" +
            "  +-------+-------+-------+\n" +
            "0 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "1 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "2 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "  +-------+-------+-------+\n" +
            "3 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "4 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "5 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "  +-------+-------+-------+\n" +
            "6 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "7 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "8 | %s %s %s | %s %s %s | %s %s %s |\n" +
            "  +-------+-------+-------+\n";
    
    /**
     * Retorna o template do tabuleiro de Sudoku com identificadores de linha e coluna
     * @return String contendo o template visual do tabuleiro
     */
    public static String getBoardTemplate() {
        return BOARD_TEMPLATE;
    }
    
}
