package com.main.collections.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Person p1 = new Person(1);
        Person p2 = new Person(2);
        Person p3 = new Person(3);
        Person p4 = new Person(4);
        List<Person> listPerson = new ArrayList<Person>();

        listPerson.add(p1);
        listPerson.add(p2);
        listPerson.add(p3);
        listPerson.add(p4);

        Person p = listPerson.get(2);
        System.out.println(p.getId());

        for (int i = 0; i < listPerson.size(); i++) {
            System.out.println(listPerson.get(i).getId());
        }

        //Xoa Phan Tu
        listPerson.remove(3);
        for (int i = 0; i < listPerson.size(); i++) {
            System.out.println(listPerson.get(i).getId());
        }
        System.out.println("---------");
        listPerson.remove(new Person(2));
        for(Person person : listPerson) {
            System.out.println(person.getId());
        }

    }
}
