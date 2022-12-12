package com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger;

public class ChickenBurger extends Burger {

	@Override
	public double price() {
		return 3;
	}

	@Override
	public String name() {
		return "Chicken Burger";
	}

}
