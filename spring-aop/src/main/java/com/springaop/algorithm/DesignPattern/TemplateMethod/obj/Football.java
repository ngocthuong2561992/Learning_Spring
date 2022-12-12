package com.springaop.algorithm.DesignPattern.TemplateMethod.obj;

import com.springaop.algorithm.DesignPattern.TemplateMethod.GameplayTemplate;

public class Football extends GameplayTemplate {
    @Override
    protected void initialize() {
        System.out.println("Init football");
    }

    @Override
    protected void startPlay() {
        System.out.println("start play football");
    }

    @Override
    protected void endPlay() {
        System.out.println("end play football");
    }
}
