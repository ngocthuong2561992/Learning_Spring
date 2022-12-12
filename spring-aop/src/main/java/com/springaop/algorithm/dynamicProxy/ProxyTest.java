package com.springaop.algorithm.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        // wrap 1000 Integers from 1 to 1000 by 1000 proxies and assign them to the Object array named "elements"
        for(int i = 0; i < elements.length; i++) {
            Integer value = i+1;

            // wrap
            InvocationHandler handler = new DebugHandler(value);

            // create proxy for the wrapped handler
            Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
            elements[i] = proxy;
        }

        // construct a random Integer to be found by Binary Search
        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println("Key: " + key);

        // search for the key
        int result = Arrays.binarySearch(elements, key);

        // print match if found
        if(result >= 0) System.out.println(elements[result].toString());

    }
}
