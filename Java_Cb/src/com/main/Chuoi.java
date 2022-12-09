package com.main;

public class Chuoi {
    public static void main(String[] args) {
        String chuoi = "Hello World";

        int count = 0;

        for(int i = 0; i < chuoi.length(); i++) {
            if (chuoi.charAt(i) == 'o') {
                count ++;
            }
        }
        System.out.println("Dem chu o : " + count);
    }
}
