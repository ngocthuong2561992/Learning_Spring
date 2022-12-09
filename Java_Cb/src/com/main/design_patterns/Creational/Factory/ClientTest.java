package com.main.design_patterns.Creational.Factory;

public class ClientTest {
    public static void main(String[] args) {
        Candy candy = CandyFactory.getCandy(CandyType.MINTY_CANDY);
        System.out.println(candy.getCandyName());
    }
}
