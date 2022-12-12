package com.springaop.algorithm.java8;

public interface Interface1 {
	public void print();
	public void print4(String s);
	default void print2() {
		System.out.println("print2");
	}
	static void print3() {
		System.out.println("print3");
	}
}
