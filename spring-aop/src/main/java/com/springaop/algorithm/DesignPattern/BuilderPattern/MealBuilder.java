package com.springaop.algorithm.DesignPattern.BuilderPattern;

public abstract class MealBuilder {
    protected Meal meal;
    abstract MealBuilder prepareMeal();

    protected Meal build() {
        return meal;
    }
}
