package br.com.sudoku.view;  // ou br.com.sudoku.view, como preferir


import br.com.sudoku.model.Cell;
import br.com.sudoku.model.Game;
import br.com.sudoku.util.BoardTemplate;

import java.util.Arrays;
import java.util.List;

public class TerminalView {

    public void printBoard(Game game) {
        String[] numbers = game.getBoard().stream()
                .flatMap(row -> row.stream())
                .map(cell -> " " + (cell.getNumber() == null ? " " : cell.getNumber().toString()))
                .toArray(String[]::new);

        String boardFormatted = String.format(BoardTemplate.BOARD_TEMPLATE, (Object[]) numbers);

        System.out.println(boardFormatted);
    }



}

