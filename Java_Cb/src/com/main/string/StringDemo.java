package com.main.string;

public class StringDemo {

    public static void main(String[] args) {
        String s = "hello";
        s = s+ "Trung Tam ";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello");
        stringBuilder.append("Trung Tâm Java");

        String s2 =stringBuilder.toString();
        System.out.println(s2);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hello 2");
        stringBuffer.append("Trung Tâm Java 2");

        String s3 =stringBuffer.toString();
        System.out.println(s3);



    }
}
