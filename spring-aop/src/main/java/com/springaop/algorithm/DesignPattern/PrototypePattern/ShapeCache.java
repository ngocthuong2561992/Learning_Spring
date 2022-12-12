package com.springaop.algorithm.DesignPattern.PrototypePattern;

import java.util.HashMap;
import java.util.Map;

public class ShapeCache {
	private static Map<String, Shape> cache = new HashMap<String, Shape>();
	
	public static void loadCache() {
		Circle cir = new Circle();
		cir.setId("1");
		cache.put(cir.getId(), cir);
		
		Rectangle rec = new Rectangle();
		rec.setId("2");
		cache.put(rec.getId(), rec);
	}
	
	public static Shape getShape(String id) {
		Shape item  = cache.get(id);
		return (Shape) item.clone();
	}
}
