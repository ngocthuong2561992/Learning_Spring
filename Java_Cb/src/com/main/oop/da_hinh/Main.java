package com.main.oop.da_hinh;

public class Main {

    public static void main(String[] args) {
        Employee emp = new Employee();

        Person p = new Employee();

        emp.setTen("A");
        emp.setLuong(4.5);

        p.setTen("B");
        //p.setLuong(4.4); --no khong duoc\

        emp.thongTin(); //class con
        p.thongTin(); //goi toi class con thong qua extends the hien tinh da hinh


        Object obj = new Employee();
        Object obj1 = new Person();
        obj.toString();
    }
}
