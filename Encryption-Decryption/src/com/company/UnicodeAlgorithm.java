package com.company;

public class UnicodeAlgorithm implements Algorithm {
    @Override
    public String encrypt(String plainText, int key) {
        if (plainText.isEmpty()) { return plainText; }
        char[] arr = plainText.toCharArray();
        for (int  i = 0; i < arr.length; i++) {
            arr[i] =(char) (arr[i] + key);
        }
        return new String(arr);
    }

    @Override
    public String decrypt(String cipherText, int key) {
        if (cipherText.isEmpty()) { return cipherText; }
        char[] arr = cipherText.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (arr[i] - key);
        }
        return new String(arr);
    }
}
