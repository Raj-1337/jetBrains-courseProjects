package com.company;

public class Main {
    public static void main(String[] args) {
        TicTac game = new TicTac();
        boolean gameStatus = true;
        String op;
        game.printBoard();
        while (gameStatus) {
            while (!game.getCrctinput());
            game.printBoard();
            game.runAnalysis();
            op = game.getAnalysis();
            if (!op.equals("Game not finished")) {
                System.out.println(op);
                gameStatus = false;
            }
        }
    }
}
