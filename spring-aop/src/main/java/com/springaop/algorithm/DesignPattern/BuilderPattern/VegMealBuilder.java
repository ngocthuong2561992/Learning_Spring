package com.springaop.algorithm.DesignPattern.BuilderPattern;

import com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger.VegBurger;
import com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Pepsi;

public class VegMealBuilder extends MealBuilder {
    @Override
    public MealBuilder prepareMeal() {
        meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Pepsi());
        return this;
    }

}
