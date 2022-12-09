package com.main.java8;

@FunctionalInterface
public interface functionalInterfaceDemo {

    //neu da khai bao @FunctionalInterface thi phai co phuong thuc neu khong no loi

    void doSomething();

    int hashCode();

    String toString();

    boolean equals(Object obj);

    default void defaultMethod1() {

    }

    default void defaultMethod2() {

    }

    static void staticMethod() {

    }
}
