package com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink;

public class Pepsi extends ColdDrink {

	@Override
	public double price() {
		return 1.5;
	}

	@Override
	public String name() {
		return "Pepsi";
	}

}
