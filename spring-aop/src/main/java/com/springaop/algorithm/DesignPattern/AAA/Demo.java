package com.springaop.algorithm.DesignPattern.AAA;

public class Demo {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .setCpu("500gb")
                .setRam("16gb")
                .build();
    }
}
