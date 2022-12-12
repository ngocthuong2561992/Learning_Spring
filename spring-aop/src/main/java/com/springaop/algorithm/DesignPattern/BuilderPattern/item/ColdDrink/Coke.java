package com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink;

public class Coke extends ColdDrink {

	@Override
	public double price() {
		return 1.3;
	}

	@Override
	public String name() {
		return "Coke";
	}

}
