package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class TicTac {
    boolean xWins;
    boolean oWins;
    boolean xTurn;
    char[][] gameBoard;
    private Scanner scanner;

    public TicTac() {
        this.xWins = false;
        this.oWins = false;
        this.xTurn = true;
        this.gameBoard = initializeGameBoard();
        this.scanner = new Scanner(System.in);
    }

    public char[][] initializeGameBoard() {
        char[][] res = new char[3][];
        char[] tmp = {' ', ' ', ' '};
        for (int i =0; i < 3; i++) {
            res[i] = Arrays.copyOf(tmp, 3);
        }
        return res;
    }

    public void printBoard() {
        System.out.println("-".repeat(9));
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %s %s %s |\n", gameBoard[i][0], gameBoard[i][1], gameBoard[i][2]);
        }
        System.out.println("-".repeat(9));
    }

    public void updateGameBoard(int x, int y, char c) {
        gameBoard[x][y] = c;
        xTurn = !xTurn;
    }

    public boolean getCrctinput() {
        System.out.print("Enter the coordinates: ");
        var s = scanner.nextLine().split("\\s+");
        try {
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            if (m > 3 || n > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            } else if (gameBoard[3 - n][m - 1] != ' '){
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else {
                updateGameBoard(3 - n, m - 1, xTurn ? 'X' : 'O');
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }


    public int getCount(char x) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == x) { count++; }
            }
        }
        return count;
    }

    public void runAnalysis() {
        checkColumns();
        checkRows();
        checkDiagnols();
    }

    public String getAnalysis() {
        if (xWins && oWins) { return "Impossible"; }
        if (!xWins && !oWins && getCount(' ') == 0) { return "Draw"; }
        if (xWins) { return "X wins"; }
        if (oWins) { return "O wins"; }
        return "Game not finished";
    }

    public void checkColumns() {
        char c;
        for (int i = 0; i < 3; i++) {
            c = gameBoard[i][0];
            if (c == ' ') { continue; }
            if (c == gameBoard[i][1] && c == gameBoard[i][2]) { logWinner(c); }
        }
    }

    public void checkRows() {
        char c;
        for (int i = 0; i < 3; i++) {
            c = gameBoard[0][i];
            if (c == ' ') { continue; }
            if (c == gameBoard[1][i] && c == gameBoard[2][i]) { logWinner(c); }
        }
    }

    public void checkDiagnols() {
        char m = gameBoard[0][0];
        char n = gameBoard[0][2];
        if (m == ' ' || n == ' ') { return; }
        if (m == gameBoard[1][1] && m == gameBoard[2][2]) { logWinner(m); }
        if (n == gameBoard[1][1] && n == gameBoard[2][0]) { logWinner(n); }
    }

    public void logWinner(char c) {
        if (c == 'X') {
            xWins = true;
        } else {
            oWins = true;
        }
    }

}

