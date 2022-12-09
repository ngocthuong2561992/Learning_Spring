package com.main.oop.truu_truong;

public abstract class Person {

    private String ten;

    public Person() {
    }

    public Person(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public abstract double luong ();
}
