package com.main.design_patterns.Structural.Facade;

public class Television implements Furniture {
    @Override
    public void make() {
        System.out.println("make a TV");
    }
}
