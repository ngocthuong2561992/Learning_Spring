package com.main;

public class Mang {
    public static void main(String[] args) {
        //Mang
        int[] a = {14,99,100,1,2,4,5,6,9,11};
        System.out.println("Mang duoc nhap la :");
        for (int i = 0; i < a.length -1; i++) {
            System.out.println(a[i]);
        }

        int temp;
        for (int i = 0; i < a.length -1; i++) {
            for(int j= i + 1; j < a.length; j++) {
                if(a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println("Mang duoc sx Tang dan la :");
        for (int i = 0; i < a.length -1; i++) {
            System.out.println(a[i]);
        }
    }
}
