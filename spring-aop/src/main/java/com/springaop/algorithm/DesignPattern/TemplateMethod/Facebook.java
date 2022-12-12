package com.springaop.algorithm.DesignPattern.TemplateMethod;

public class Facebook extends NetworkTemplate {
    @Override
    void login() {
        System.out.println("Login success facebook");
    }

    @Override
    void logout() {
        System.out.println("Logout success facebook");
    }
}
