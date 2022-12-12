package com.springaop.algorithm.DesignPattern.FactoryPattern;

public class FactoryPatternDemo {
	public static void main(String[] args) {
		Shape shape = ShapeFactory.getShape("CIRCLE");
		shape.draw();
		
		shape = ShapeFactory.getShape("SQUARE");
		shape.draw();
	}
}
