package com.springaop.algorithm.DesignPattern.TemplateMethod.obj;

import com.springaop.algorithm.DesignPattern.TemplateMethod.GameplayTemplate;

public class Dota extends GameplayTemplate {
    @Override
    protected void initialize() {
        System.out.println("Init dota");
    }

    @Override
    protected void startPlay() {
        System.out.println("start play dota");
    }

    @Override
    protected void endPlay() {
        System.out.println("end play dota");
    }
}
