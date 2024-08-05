package com.smallcase;
public class randomStringGenerator {
    public static String generateRandomString(Integer length) {
        String randomString = "";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            randomString += characters.charAt(randomIndex);
        }
        return randomString;
    }
}