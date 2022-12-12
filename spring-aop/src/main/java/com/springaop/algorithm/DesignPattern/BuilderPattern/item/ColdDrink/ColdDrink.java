package com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink;

import com.springaop.algorithm.DesignPattern.BuilderPattern.item.Item;
import com.springaop.algorithm.DesignPattern.BuilderPattern.packing.Bottle;
import com.springaop.algorithm.DesignPattern.BuilderPattern.packing.Packing;

public abstract class ColdDrink implements Item {

	@Override
	public Packing packing() {
		return new Bottle();
	}
}
