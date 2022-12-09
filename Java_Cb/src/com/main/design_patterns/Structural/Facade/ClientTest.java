package com.main.design_patterns.Structural.Facade;

public class ClientTest {
    public static void main(String[] args) {
        FurnitureFacade facade = FurnitureFacade.getInstance();
//        facade.makeTableAndChair();
        facade.makeTVAndChair();
    }
}
