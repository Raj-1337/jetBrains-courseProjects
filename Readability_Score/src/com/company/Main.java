package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input;
        String choice;
        Scanner scanner = new Scanner(System.in);
        try  {
            input = Files.readString(Paths.get("./", args[0]));
        } catch (IOException e) {
            input = "";
            System.out.println("error in opening file " +  e.getMessage());
        }
        ReadabilityIndex readabilityIndex = new ReadabilityIndex(input);
        readabilityIndex.display();
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        choice = scanner.next();
        System.out.println();
        switch (choice) {
            case "ARI":
                readabilityIndex.ARI();
                break;
            case "FK":
                readabilityIndex.FK();
                break;
            case "SMOG":
                readabilityIndex.SMOG();
                break;
            case "CL":
                readabilityIndex.CL();
                break;
            case "all":
                readabilityIndex.ALL();
                break;
        }
    }
}
