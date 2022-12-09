package com.main.design_patterns.Structural.Facade;

public class Chair implements Furniture {
    @Override
    public void make() {
        System.out.println("make a chair");
    }
}
