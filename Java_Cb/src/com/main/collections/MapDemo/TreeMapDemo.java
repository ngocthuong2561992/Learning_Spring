package com.main.collections.MapDemo;


import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1,"B");
        map.put(2,"A");  //chu y A
        map.put(2,"D"); //chu y D D chồng lên truoc
        map.put(3,"E");
        map.put(4,"C");
        map.put(5,"A");
        // map.put(null, "D");  ko null
        map.put(7, null); //chu y nhe

        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        map.remove(1);
        System.out.println("------------------------------------");
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
