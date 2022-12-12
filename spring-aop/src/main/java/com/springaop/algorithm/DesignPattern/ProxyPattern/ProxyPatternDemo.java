package com.springaop.algorithm.DesignPattern.ProxyPattern;

public class ProxyPatternDemo {
	public static void main(String[] args) {
		/*
		The Proxy pattern uses a proxy (surrogate) object “in place of” another object.
		The objective of a proxy object is to control the creation of and access to
		the real object it represents. A common use of a proxy is to defer the cost of
		instantiating of an object (that is expensive to create) until it is actually
		needed by clients
		 */
		Image image = new ProxyImage("test_10mb.jpg");

		//image will be loaded from disk
		image.display(); 
		System.out.println("");
	      
		//image will not be loaded from disk
		image.display(); 
		
	}
}
