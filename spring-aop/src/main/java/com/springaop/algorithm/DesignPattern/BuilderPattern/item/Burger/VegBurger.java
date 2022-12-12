package com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger;

public class VegBurger extends Burger {

	@Override
	public double price() {
		return 5;
	}

	@Override
	public String name() {
		return "Veg Burger";
	}

}
