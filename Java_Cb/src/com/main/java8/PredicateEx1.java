package com.main.java8;

import java.util.function.Predicate;

public class PredicateEx1 {
     public static void main(String[] args) {
         // Predicate String
         Predicate<String> predicateString = s -> {
             return s.equals("gpcoder");
         };
         System.out.println(predicateString.test("gpcoder")); // true
         System.out.println(predicateString.test("GP Coder")); // false

         // Predicate integer
         Predicate<Integer> predicateInt = i -> {
             return i > 0;
         };
         System.out.println(predicateInt.test(1)); // true
         System.out.println(predicateInt.test(-1)); // false
    }
}
