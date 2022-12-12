package com.springaop.algorithm.DesignPattern.BuilderPattern.item;

import com.springaop.algorithm.DesignPattern.BuilderPattern.packing.Packing;

public interface Item {
	public double price();
	public String name();
	public Packing packing();
}
