package com.springaop.algorithm.DesignPattern.BridgePattern;

public class RedCircle implements DrawApi {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Red, x: " + x + ", y: " + y);
	}

}
