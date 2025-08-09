package br.com.sudoku.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Game {
    private final List<List<Cell>> board; // agora direto aqui
    private GameStatus status;

    public Game(String[] args) {
        this.status = GameStatus.NAO_INICIADO;

        // crio uma matriz 9X9 onde todas as celulas serao incializados como não fixa e nulas
        this.board = IntStream.range(0, 9)
                .mapToObj(i -> IntStream.range(0, 9)
                        .mapToObj(j -> new Cell(0, false, i, j))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        //percorre os argumentos fornecidos e preenhcer a estrutura de dados Board
        Arrays.stream(args).forEach(arg -> {
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
                    this.board.get(row).set(col, cell);
                } else {
                    System.out.println("Coordenadas inválidas no argumento: " + arg);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar argumento: " + arg + " - " + e.getMessage());
            }
        });
    }

    public List<List<Cell>> getBoard() {
        return board;
    }



}
