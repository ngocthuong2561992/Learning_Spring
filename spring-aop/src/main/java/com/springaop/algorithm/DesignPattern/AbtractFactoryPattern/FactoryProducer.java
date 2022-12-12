package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class FactoryProducer {
    public static GuiFactory getFactory(Platform platform) {
        switch (platform) {
            case MAC -> {
                return new MacFactory();
            }
            case WIN -> {
                return new WinFactory();
            }
            default -> {
                return null;
            }
        }
    }
}
