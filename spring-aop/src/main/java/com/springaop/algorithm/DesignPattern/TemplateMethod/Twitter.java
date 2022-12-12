package com.springaop.algorithm.DesignPattern.TemplateMethod;

public class Twitter extends NetworkTemplate {
    @Override
    void login() {
        System.out.println("Login success Twitter");
    }

    @Override
    void logout() {
        System.out.println("Logout success Twitter");
    }
}
