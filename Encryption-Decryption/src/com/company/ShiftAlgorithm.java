package com.company;

public class ShiftAlgorithm implements Algorithm {
    @Override
    public String encrypt(String plainText, int key) {
        if (plainText.isEmpty()) { return plainText; }
        char[] arr = plainText.toCharArray();
        int t;
        int u;
        for (int i = 0; i < arr.length; i++) {
            t = arr[i];
            u = t + key;
            if (t >= 97 && t <= 122) {
                arr[i] =  (char) (u > 122 ? 96 + (u % 122) : u);
            } else if (t >= 65 && t <= 90 ) {
                arr[i] = (char) (u > 90 ? 64 + ( u % 90 ) : u);
            }
        }
        return new String(arr);
    }

    @Override
    public String decrypt(String cipherText, int key) {
        if (cipherText.isEmpty()) { return cipherText; }
        char[] arr = cipherText.toCharArray();
        int t;
        int u;
        for (int i = 0; i < arr.length; i++) {
            t = arr[i];
            u = t - key;
            if (t >= 97 && t <= 122) {
                arr[i] =  (char) (u < 97 ? 123 - (97 % u) : u);
            } else if (t >= 65 && t <= 90 ) {
                arr[i] = (char) (u < 65 ? 91 - ( 65 % u ) : u);
            }
        }
        return new String(arr);
    }
}
