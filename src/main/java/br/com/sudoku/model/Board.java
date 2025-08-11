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

    public int hasErros() {
        return (int) cells.stream()
                .flatMap(List::stream)
                .filter(cell -> !cell.isFixed())
                .filter(cell -> !cell.isValid())
                .count();
    }

    public boolean hasInsertedNumber() {
        return cells.stream()
                .flatMap(List::stream)
                .anyMatch(cell -> !cell.isFixed() && cell.getNumber() != null);
    }

    public boolean isFull() {
        return cells.stream()
                .flatMap(List::stream)
                .filter(cell -> !cell.isFixed())
                .allMatch(cell -> cell.getNumber() != null);
    }

    public GameStatus getGameStatus() {
        if (!hasInsertedNumber()) {
            return GameStatus.NAO_INICIADO;
        }
        if (!isFull()) {
            return GameStatus.INCOMPLETO;
        }
        return GameStatus.COMPLETO;
    }

    public void clearBoard() {
        cells.stream()
                .flatMap(List::stream)
                .forEach(Cell::clean);  // cada célula decide se limpa ou não
    }

}
