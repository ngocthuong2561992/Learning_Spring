package com.springaop.algorithm.DesignPattern.BridgePattern;

public class BridgeDemo {
	public static void main(String[] args) {
	/*
	Decouple an abstraction from its implementation so that the two can vary independently.
	 */
		Circle red = new Circle(3, 6, 2, new RedCircle());
		red.draw();
		
		Circle green = new Circle(15, 20, 7, new GreenCircle());
		green.draw();
	}
}
