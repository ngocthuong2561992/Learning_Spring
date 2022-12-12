package com.springaop.algorithm.DesignPattern.DecoratorPattern;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
	/*
	Attach additional responsibilities to an object dynamically.
	Decorators provide a flexible alternative to subclassing for extending functionality.
	*/
		Shape circle = new Circle();

		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		System.out.println("\nCircle of red border");
		redCircle.draw();

		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}
}
