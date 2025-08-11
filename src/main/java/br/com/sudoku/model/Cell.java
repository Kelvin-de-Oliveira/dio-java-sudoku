package br.com.sudoku.model;

public class Cell {
    private Integer number;
    private final boolean fixed;
    private int row;
    private int col;
    private final int pretend;

    public Cell(int pretend, boolean fixed, int row, int col) {
        this.pretend = pretend;
        this.fixed = fixed;
        this.row = row;
        this.col = col;
        if (fixed) {
            this.number = pretend;
        } else {
            this.number = null;
        }
    }

    public Integer getNumber() {
        return number;
    }

    public boolean setNumber(Integer number) {
        if (fixed) {
            return false;
        }
        this.number = number;
        return true;
    }
    public boolean removeNumber() {
        return number != null && setNumber(null);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPretend() {
        return pretend;
    }

    public void clean(){
        if(!fixed){
            this.number = null;
        }
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isFilled() {
        return number != null;
    }

    public boolean isValid() {
        if (number == null) {
            return true;
        }
        return number.equals(pretend);
    }


}
