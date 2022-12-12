package com.springaop.algorithm.DesignPattern.TemplateMethod;

public abstract class NetworkTemplate {
    abstract void login();
    abstract void logout();

    protected void sendMessage(String message) {
        login();
        System.out.println("send: " + message);
        logout();
    }
}
