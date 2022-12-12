package com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink;

public class Milkshake extends ColdDrink {

	@Override
	public double price() {
		return 2.4;
	}

	@Override
	public String name() {
		return "Milk shake";
	}

}
