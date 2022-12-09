package com.main.collections.SetDemo;


import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {
        Set<String> setString = new TreeSet<>();

        setString.add("3");
        setString.add("2");
        setString.add("1");
     // setString.add(null);  ---khong duoc null

        for (String s:setString) {
            System.out.println(s);
        }

        setString.remove("A");
    }
}
