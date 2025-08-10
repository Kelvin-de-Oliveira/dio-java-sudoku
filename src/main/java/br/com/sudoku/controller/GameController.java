package br.com.sudoku.controller;

import br.com.sudoku.model.Board;
import br.com.sudoku.model.Cell;
import br.com.sudoku.view.TerminalView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameController {
    private final TerminalView view;
    private Board board;

    public GameController(TerminalView view) {
        this.view = view;
    }

    public Board getBoard() {
        return board;
    }

    public void runGame(String initialValues) {
        boolean start = view.askYesNo("Deseja iniciar um novo o jogo? (s/n)");

        if (!start) {
            view.printMessage("Programa encerrado.");
            return;
        }

        prepareBoard(initialValues);
        view.printBoard(board);

        boolean running = true;
        while (running) {
            int option = view.showMenuAndGetOption();
            switch (option) {
                case 1: // Adicionar número
                    int row = view.runUntilGetValidNumber(0, 8, "Informe a linha (0-8): ");
                    int col = view.runUntilGetValidNumber(0, 8, "Informe a coluna (0-8): ");
                    int number = view.runUntilGetValidNumber(1, 9, "Informe o número a adicionar (1-9): ");

                    boolean success = board.setCellNumber(row, col, number);
                    if (success) {
                        view.printMessage("Número adicionado com sucesso.");
                    } else {
                        view.printMessage("Não é possível alterar uma célula fixa.");
                    }
                    break;
                case 2: // TODO Remover número

                    break;
                case 3: // TODO Verificar jogo

                    break;
                case 4: // TODO Verificar status do jogo

                    break;
                case 5: // TODO Limpar tudo

                    break;
                case 6: // Finalizar jogo
                    running = false;
                    break;
                default:
                    view.printMessage("Opção inválida.");
            }
        }

        view.printMessage("Jogo finalizado.");
    }
    public void prepareBoard(String initialValuesString) {
        List<List<Cell>> cells = IntStream.range(0, 9)
                .mapToObj(row -> IntStream.range(0, 9)
                        .mapToObj(col -> new Cell(0, false, row, col))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        String[] initialValues = initialValuesString.trim().split("\\s+");

        for (String arg : initialValues) {
            try {
                String[] sections = arg.split(";");
                String[] pos = sections[0].split(",");
                String[] data = sections[1].split(",");

                int row = Integer.parseInt(pos[0]);
                int col = Integer.parseInt(pos[1]);
                int number = Integer.parseInt(data[0]);
                boolean fixed = Boolean.parseBoolean(data[1]);

                if (row >= 0 && row < 9 && col >= 0 && col < 9) {
                    Cell cell = new Cell(number, fixed, row, col);
                    cells.get(row).set(col, cell);
                } else {
                    view.printMessage("Coordenadas inválidas no argumento: " + arg);
                }
            } catch (Exception e) {
                view.printMessage("Erro ao processar argumento: " + arg + " - " + e.getMessage());
            }
        }

        this.board = new Board(cells);
        //view.printBoard(board);
    }


}
