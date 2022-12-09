package com.main;

public class Static {
    static int count = 2;

    public static int ChuVi(int a, int b) {
        return (a+b)*2;
    }

    public static void main(String[] args) {
        System.out.println(Static.count);
        System.out.println(Static.ChuVi(5,7));

        Static hh1 = new Static();
        Static hh2 = new Static();

        hh1.count = 5;
        System.out.println(count);
        hh2.count = 10;
        System.out.println(hh2.ChuVi(5,7));
    }
}
