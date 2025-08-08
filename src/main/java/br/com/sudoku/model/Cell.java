package br.com.sudoku.model;

public class Cell {
    private Integer number;
    private boolean fixed;
    private int row;
    private int col;

    public Cell(Integer number, boolean fixed, int row, int col) {
        this.number = number;
        this.fixed = fixed;
        this.row = row;
        this.col = col;
    }

    public Integer getnumber() {
        return number;
    }

    public void setnumber(Integer number) {
        if (fixed) return;
        this.number = number;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }


}
