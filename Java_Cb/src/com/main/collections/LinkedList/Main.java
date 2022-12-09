package com.main.collections.LinkedList;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Person p1 = new Person(1);
        Person p2 = new Person(2);
        List<Person> listPerson = new LinkedList<>();

        listPerson.add(p1);
        listPerson.add(p2);

        for (int i = 0; i < listPerson.size(); i++) {
            System.out.println(listPerson.get(i).getId());
        }
        System.out.println("---------");
        //Xoa Phan Tu
        listPerson.remove(0);
        for(Person person : listPerson) {
            System.out.println(person.getId());
        }
    }
}
