package com.company;

public class AlgorithmFactory {
    public static Algorithm getInstance(String type) {
        if (type.equals("unicode")) {
            return new UnicodeAlgorithm();
        }
        return new ShiftAlgorithm();
    }
}
