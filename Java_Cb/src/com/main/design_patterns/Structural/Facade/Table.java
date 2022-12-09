package com.main.design_patterns.Structural.Facade;

public class Table implements Furniture {
    @Override
    public void make() {
        System.out.println("Make a table");
    }
}
