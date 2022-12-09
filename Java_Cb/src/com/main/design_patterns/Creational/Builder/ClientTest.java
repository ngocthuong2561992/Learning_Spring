package com.main.design_patterns.Creational.Builder;

public class ClientTest {
    public static void main(String[] args) {
        House house1 = new HouseBuilder().buildWalls(4).buildDoors(5).buildColor("Red").build();
        System.out.println(house1);
    }
}
