package br.com.sudoku;

import br.com.sudoku.controller.GameController;
import br.com.sudoku.view.TerminalView;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java br.com.sudoku.Main \"0,0;4,false 1,0;7,false ...\"");
            return;
        }

        String initialValuesString = args[0];

        TerminalView view = new TerminalView();
        GameController controller = new GameController(view);

        controller.runGame(initialValuesString);
    }
}