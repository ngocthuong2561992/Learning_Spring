package com.main.collections.Vector;



import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person(1);
        Person p2 = new Person(2);
        List<Person> listPerson = new Vector<>();

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
