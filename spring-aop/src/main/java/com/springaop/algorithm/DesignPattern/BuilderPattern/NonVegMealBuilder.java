package com.springaop.algorithm.DesignPattern.BuilderPattern;

import com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger.ChickenBurger;
import com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Milkshake;

public class NonVegMealBuilder extends MealBuilder {
    @Override
    public MealBuilder prepareMeal() {
        meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Milkshake());
        return this;
    }

}
