package com.main.design_patterns.Creational.Builder;

public interface Builder {
    Builder buildRoof(boolean hasRoof);

    Builder buildPool(boolean hasPool);

    Builder buildWalls(int walls);

    Builder buildDoors(int doors);

    Builder buildColor(String color);

    House build();
}
