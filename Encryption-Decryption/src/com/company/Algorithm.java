package com.company;

public interface Algorithm {
    public String encrypt(String plainText, int key);
    public String decrypt(String cipherText, int key);
}
