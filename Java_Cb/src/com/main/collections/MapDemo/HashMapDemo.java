package com.main.collections.MapDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>();

        map.put(1,"B");
        map.put(2,"A");  //chu y A
        map.put(2,"D"); //chu y D D chồng lên truoc
        map.put(3,"E");
        map.put(4,"C");
        map.put(5,"A");
        map.put(null, "D");
        map.put(7, null);

//        Set<Integer> keySet = map.keySet();
//
//        System.out.println(map.get(null));
//        for (Integer s:keySet) {
//            System.out.println(s + " " + map.get(s));
//        }

        System.out.println("-----------------");
//        map.remove(1);
//        map.clear();

        Set<Integer> keySet2 = map.keySet();
        map.remove(1);
        map.clear();

        for (Integer i:keySet2) {
            System.out.println(i + " " + map.get(i));
        }

//        Set<Integer> keySet2 = map.keySet();
//        for (Integer s:keySet2) {
//            System.out.println(s + " " + map.get(s));
//        }


    }
}
