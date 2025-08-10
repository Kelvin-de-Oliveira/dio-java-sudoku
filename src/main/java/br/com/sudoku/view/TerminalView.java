package br.com.sudoku.view;  // ou br.com.sudoku.view, como preferir


import br.com.sudoku.model.Board;
import br.com.sudoku.util.BoardTemplate;

import java.util.Scanner;

public class TerminalView {

    private final Scanner scanner = new Scanner(System.in);

    public boolean askYesNo(String message) {
        System.out.println(message);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("sim");
    }

    public int askInt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. " + message);
            }
        }
    }

    public int runUntilGetValidNumber(final int min, final int max, final String message) {
        int current = askInt(message);
        while (current < min || current > max) {
            System.out.printf("ERRO: a entrada deve ser um numero entre %d e %d\n", min, max);
            current = askInt(message);
        }
        return current;
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

