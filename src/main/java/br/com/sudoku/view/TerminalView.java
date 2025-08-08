package br.com.sudoku.view;  // ou br.com.sudoku.view, como preferir

import br.com.sudoku.model.Board;
import br.com.sudoku.model.Cell;
import br.com.sudoku.util.BoardTemplate;

import java.util.List;

public class TerminalView {

    public void printBoard(Board board) {
        List<List<Cell>> cells = board.getCells();

        // crio um array com 81 strings para armazenar os valores que irão preenhcer o board
        String[] numbers = new String[81];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell c = cells.get(i).get(j);
                Integer num = c.getnumber();
                numbers[i * 9 + j] = (num == null) ? " " : num.toString();
            }
        }

        // usa o BOARD_TEMPLATE com 81 placeholders %s
        String boardformatted = String.format(BoardTemplate.BOARD_TEMPLATE, (Object[]) numbers);

        System.out.println(boardformatted);
    }
}

