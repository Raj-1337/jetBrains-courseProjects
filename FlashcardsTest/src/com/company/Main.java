package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ip;
        CardsManager game = new CardsManager();
        game.argParser(args);
        while (true) {
            game.recordAndOut("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):", false);
            ip = scanner.nextLine();
            game.recordAndOut(ip, true);
            switch (ip) {
                case "add":
                    game.addCard();
                    break;
                case "remove":
                    game.removeCard();
                    break;
                case "ask":
                    game.ask();
                    break;
                case "import":
                    game.loadFile();
                    break;
                case "export":
                    game.exportFile();
                    break;
                case "log":
                    game.logIt();
                    break;
                case "hardest card":
                    game.getHardestQuestions();
                    break;
                case "reset stats":
                    game.resetStats();
                    break;
                case "exit":
                    game.exitCards();
                    return;
            }
            System.out.println();
        }
    }
}
