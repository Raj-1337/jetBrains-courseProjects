package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encryptor {

    String mode = "enc";
    int key = 0;
    String data = "";
    boolean fromFile = false;
    boolean toFile = false;
    String opFile = "";
    Algorithm algorithm = AlgorithmFactory.getInstance("");

    public Encryptor(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    if (!fromFile) {
                        data = args[i + 1];
                    }
                    break;
                case "-in":
                    fromFile = true;
                    loadInput(args[i + 1]);
                    break;
                case "-out":
                    toFile = true;
                    opFile = args[i + 1];
                    break;
                case "-alg":
                    if (args[i+1].equals("unicode")) {
                        algorithm = AlgorithmFactory.getInstance(args[i+1]);
                    }
            }
        }
    }

    public void run() {
        if (mode.equals("enc")) {
            if (!toFile) {
                System.out.println(encrypt(data, key));
            } else {
                writeOutput(opFile, encrypt(data, key));
            }
        } else {
            if (!toFile) {
                System.out.println(decrypt(data, key));
            } else {
                writeOutput(opFile, decrypt(data, key));
            }
        }
    }

    public String encrypt(String plainText, int cipherKey) {
        return algorithm.encrypt(plainText, cipherKey);
    }

    public String decrypt(String cipherText, int cipherKey) {
        return algorithm.decrypt(cipherText, cipherKey);
    }
    public void writeOutput(String fname, String data) {
        try {
            Files.writeString(Paths.get("./", fname), data);
        } catch (IOException e) {
            System.out.println("Error in writing output to file" + e);
        }
    }

    public void loadInput(String fname) {
        try {
            data = Files.readString(Paths.get("./", fname));
        } catch (IOException e) {
            System.out.println("Error in opening the requested file" + e);
        }
    }
}
