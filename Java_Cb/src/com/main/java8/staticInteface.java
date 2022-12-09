package com.main.java8;

public interface staticInteface {
    default void print() {
        if (isValid())
            System.out.println("Vehicle printed");
    }

    static boolean isValid() {
        System.out.println("Vehicle is valid");
        return true;
    }

    void showLog();

    class Car implements staticInteface {

        @Override
        public void showLog() {
            print();
            staticInteface.isValid();
        }
    }

}
