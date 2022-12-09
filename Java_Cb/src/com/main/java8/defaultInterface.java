package com.main.java8;

public interface defaultInterface {

    void draw();

    default void setColor(String color) {
        System.out.println("Draw shape with color " + color);
    }

}


interface Interface1 {
    default void doSomething() {

    }
}

interface Interface2 {
    default void doSomething() {

    }
}

class MultiInheritance implements defaultInterface, Interface1, Interface2 {

    @Override
    public void draw() {

    }

    @Override
    public void setColor(String color) {
        defaultInterface.super.setColor(color);
    }

    @Override
    public void doSomething() {
        Interface1.super.doSomething();  //chu y nhe
    }
    //Nếu một lớp con thừa kế một phương thức (abstract hoặc non-abstract) từ một super class và
    // một phương thức cùng tên trong các super interface, thì lớp con sẽ thừa kế phương thức của super class và các phương thức của interface sẽ bị bỏ qua.
}

interface Interface3 {
    default void doSomething() {
        System.out.println("Execute in Interface3");
    }
}

class Parent {
    public void doSomething() {
        System.out.println("Execute in Parent");
    }
}

class MultiInheritance2 extends Parent implements Interface3 {

    public static void main(String[] args) {
        MultiInheritance2 m = new MultiInheritance2();
        m.doSomething(); // Execute in Parent
    }

}
