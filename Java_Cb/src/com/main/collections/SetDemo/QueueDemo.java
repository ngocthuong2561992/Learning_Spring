package com.main.collections.SetDemo;

import java.util.LinkedList;
import java.util.Queue;


public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("A");
        queue.add("C");
        queue.add("B");
        queue.add("D");
        queue.add("G");
        queue.add("E");
        queue.add("A");
        queue.add(null);

        for (String s:queue) {
            System.out.println(s);
        }
        System.out.println("Sau khi xoa");
        queue.remove();



    }
}
