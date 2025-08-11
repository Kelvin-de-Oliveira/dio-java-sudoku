package br.com.sudoku.controller;

import br.com.sudoku.model.Board;
import br.com.sudoku.model.Cell;
import br.com.sudoku.model.GameStatus;
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
                case 2: // Remover número
                    int rowToRemove = view.runUntilGetValidNumber(0, 8, "Informe a linha (0-8) da célula que deseja limpar: ");
                    int colToRemove = view.runUntilGetValidNumber(0, 8, "Informe a coluna (0-8) da célula que deseja limpar: ");

                    boolean removed = board.removeCellNumber(rowToRemove, colToRemove);
                    if (removed) {
                        view.printMessage("Número removido com sucesso.");
                    } else {
                        view.printMessage("Não é possível remover uma célula fixa.");
                    }
                    break;
                case 3: // Verificar jogo
                    view.printBoard(board);
                    break;
                case 4: // Verificar status do jogo
                    GameStatus status = board.getGameStatus();
                    int errors = board.hasErrors();

                    switch (status) {
                        case NAO_INICIADO:
                            view.printMessage("O jogo ainda não foi iniciado. Nenhum número inserido pelo jogador.");
                            break;
                        case INCOMPLETO:
                            view.printMessage("O jogo está incompleto.");
                            if (errors > 0) {
                                view.printMessage("O jogo possui " + errors + " erro(s).");
                            } else {
                                view.printMessage("Nenhum erro encontrado até agora.");
                            }
                            break;
                        case COMPLETO:
                            view.printMessage("O jogo está completo.");
                            if (errors > 0) {
                                view.printMessage("Porém possui " + errors + " erro(s).");
                            } else {
                                view.printMessage("Parabéns! Nenhum erro encontrado.");
                            }
                            break;
                    }
                    break;
                case 5: // Limpar tudo
                    board.clearBoard();
                    view.printMessage("Todos os números inseridos foram removidos. O tabuleiro foi limpo.");
                    view.printMessage("O status do jogo agora é: NÃO INICIADO.");
                    view.printBoard(board);
                    break;
                case 6: // Finalizar jogo - validar antes de encerrar
                    GameStatus currentStatus = board.getGameStatus();
                    int currentErrors = board.hasErrors();

                    if (currentStatus == GameStatus.COMPLETO && currentErrors == 0) {
                        view.printMessage("Parabéns! O jogo está completo e sem erros. Finalizando partida.");
                        running = false;
                    } else if (currentStatus != GameStatus.COMPLETO) {
                        view.printMessage("O jogo ainda não está completo. Existem células a serem preenchidas.");
                    } else {
                        view.printMessage("O jogo possui " + currentErrors + " erro(s). Corrija-os antes de finalizar.");
                    }
                    break;
                case 7: // Encerrar jogo de fato
                    running = false;
                    view.printMessage("Jogo encerrado pelo usuário.");
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
