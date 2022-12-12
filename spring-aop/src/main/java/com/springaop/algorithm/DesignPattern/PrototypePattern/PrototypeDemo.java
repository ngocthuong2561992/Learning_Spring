package com.springaop.algorithm.DesignPattern.PrototypePattern;

public class PrototypeDemo {
	public static void main(String[] args) {
		ShapeCache.loadCache();
		Shape item1 = ShapeCache.getShape("1");
		System.out.println("1: " + item1.getType());
		
		Shape item2 = ShapeCache.getShape("2");
		System.out.println("2: " + item2.getType());
	}
}
