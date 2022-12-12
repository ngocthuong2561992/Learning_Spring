package com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger;

import com.springaop.algorithm.DesignPattern.BuilderPattern.item.Item;
import com.springaop.algorithm.DesignPattern.BuilderPattern.packing.Packing;
import com.springaop.algorithm.DesignPattern.BuilderPattern.packing.Wrapper;

public abstract class Burger implements Item {

	@Override
	public Packing packing() {
		return new Wrapper();
	}
}
