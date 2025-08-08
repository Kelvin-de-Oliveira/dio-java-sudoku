package br.com.sudoku.model;

import java.util.List;

public class Board {
    private List<List<Cell>> cells;

    public Board(List<List<Cell>> cells) {
        this.cells = cells;
    }
    public List<List<Cell>> getCells() {
        return cells;
    }
}
