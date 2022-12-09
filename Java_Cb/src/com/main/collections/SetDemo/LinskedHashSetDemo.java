package com.main.collections.SetDemo;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinskedHashSetDemo {
        public static void main(String[] args) {
            Set<Person> setString = new LinkedHashSet<>();

            setString.add(new Person(3));
            setString.add(new Person(2));
            setString.add(new Person(1));
            setString.add(null);

            for (Person s:setString) {
                System.out.println(s);
            }
        }
}
