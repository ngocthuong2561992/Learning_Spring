package com.springaop.algorithm.DesignPattern.FactoryPattern;

public class ShapeFactory {
	public static Shape getShape(String type) {
		if(type == null) {
			return null;
		}else if(type.equals("CIRCLE")) {
			return new Circle();
		}else if(type.equals("SQUARE")) {
			return new Square();
		}
		return null;
	}
}
