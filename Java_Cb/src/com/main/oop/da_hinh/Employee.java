package com.main.oop.da_hinh;

public class Employee extends Person {
    private double luong;

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String thongTin() {
       // return super.thongTin();
        return "Class con";
    }
}
