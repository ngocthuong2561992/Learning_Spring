package com.springaop.algorithm.DesignPattern.BridgePattern;

public class Circle extends Shape {

	private int x, y, radius;
	
	protected Circle(int x, int y, int radius, DrawApi drawApi) {
		super(drawApi);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	@Override
	public void draw() {
		drawApi.drawCircle(radius, x, y);
	}

}
