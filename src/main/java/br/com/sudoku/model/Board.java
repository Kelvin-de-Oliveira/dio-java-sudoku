package br.com.sudoku.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Board {
    private final List<List<Cell>> cells;

    public Board(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public boolean setCellNumber(int row, int col, int number) {
        Cell cell = cells.get(row).get(col);
        if (cell.isFixed()) {
            return false;
        }
        cell.setNumber(number);
        return true;
    }
    public boolean removeCellNumber(int row, int col) {
        Cell cell = cells.get(row).get(col);
        if (cell.isFixed()) {
            return false;
        }
        cell.setNumber(null);
        return true;
    }

}
