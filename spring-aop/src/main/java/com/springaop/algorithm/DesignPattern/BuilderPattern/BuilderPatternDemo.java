package com.springaop.algorithm.DesignPattern.BuilderPattern;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		Meal nonVegMeal = new NonVegMealBuilder()
				.prepareMeal()
				.build();
		nonVegMeal.show("Non Veg Meal");

		System.out.println();

		Meal vegMeal = new VegMealBuilder()
				.prepareMeal()
				.build();
		vegMeal.show("Veg Meal");

		Computer computer = new Computer.Builder()
				.setRAM("2 GB")
				.setHDD("500 GB")
				.build();
	}
}
