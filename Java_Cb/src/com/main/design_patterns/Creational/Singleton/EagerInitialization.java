package com.main.design_patterns.Creational.Singleton;

public class EagerInitialization {
    private static final EagerInitialization INSTANCE =new EagerInitialization();
    private EagerInitialization(){

    }
    public static EagerInitialization getInstance(){
        return INSTANCE;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
