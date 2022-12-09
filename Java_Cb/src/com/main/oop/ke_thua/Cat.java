package com.main.oop.ke_thua;

public class Cat extends Animal {
    String ten;
    public void thongTinCat() {
        //ten ="A" ;  ---vi su dung private
        tuoi = 12;
        noiSinhSong = "Hanoi";
        diachi = "VN";

        //anUong(); vi private
        daoChoi();
        thongTin();
        diLai();

        //su khac nhau this va supper
        super.thongTin();
        this.ten = "B";
    }
}
