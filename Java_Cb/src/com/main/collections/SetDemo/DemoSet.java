package com.main.collections.SetDemo;

import java.util.HashSet;
import java.util.Set;

public class DemoSet {
    public static void main(String[] args) {
        Set<String> setString = new HashSet<>();

        setString.add("B");
        setString.add("A");
        setString.add("E");
        setString.add("C");
        setString.add("A");
        setString.add(null);

        for (String s:setString) {
            System.out.println(s);
        }
    }
}
