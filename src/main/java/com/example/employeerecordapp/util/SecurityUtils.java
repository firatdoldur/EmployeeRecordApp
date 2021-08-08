package com.example.employeerecordapp.util;

public class SecurityUtils {

    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final static int MAX_INDEX = 61;
    private final static int MIN_INDEX = 1;

    public static String generatePublicId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            int randomIdx = randomIndex();
            builder.append(ALPHABET.charAt(randomIdx));
        }
        return builder.toString();
    }

    private static int randomIndex() {
        return MIN_INDEX + (int) (Math.random() * ((MAX_INDEX - MIN_INDEX) + 1));
    }
}