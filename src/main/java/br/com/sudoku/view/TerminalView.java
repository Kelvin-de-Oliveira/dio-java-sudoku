package br.com.sudoku.view;  // ou br.com.sudoku.view, como preferir


import br.com.sudoku.model.Board;
import br.com.sudoku.util.BoardTemplate;

import java.util.Scanner;

public class TerminalView {

    public boolean askYesNo(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("sim");
    }

    public int showMenuAndGetOption() {
        System.out.println("Menu:");
        System.out.println("1 - Adicionar número");
        System.out.println("2 - Remover número");
        System.out.println("3 - Verificar jogo");
        System.out.println("4 - Verificar status do jogo");
        System.out.println("5 - Limpar tudo");
        System.out.println("6 - Finalizar jogo");
        System.out.print("Escolha uma opção: ");

        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void printBoard(Board board) {
        String[] numbers = board.getCells().stream()
                .flatMap(row -> row.stream())
                .map(cell -> " " + (cell.getNumber() == null ? " " : cell.getNumber().toString()))
                .toArray(String[]::new);

        String boardFormatted = String.format(BoardTemplate.BOARD_TEMPLATE, (Object[]) numbers);

        System.out.println(boardFormatted);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}

