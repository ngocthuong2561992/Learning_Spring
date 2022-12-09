package com.main.java8;

import java.util.Optional;

class Student {
    String name;
}

public class OptionalEx1 {

    public void preJava8() {
        Student student = getStudent();
        if (student != null) {
            System.out.println(student.name);
        }
    }

    public void java8() {
        Student student = getStudent();
        Optional<Student> opt =  Optional.of(student);
        if (opt.isPresent()) {
            System.out.println(opt.get().name);
        }
    }
    private Student getStudent() {
        Student student = new Student();
        student.name = "ABC";
        return null;
    }
}
