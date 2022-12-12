package com.springaop.algorithm.DesignPattern.BridgePattern;

public class GreenCircle implements DrawApi {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Green, x: " + x + ", y: " + y);
	}

}
