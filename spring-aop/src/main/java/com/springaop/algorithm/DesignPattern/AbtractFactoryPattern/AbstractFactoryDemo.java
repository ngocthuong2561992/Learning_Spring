package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        handle(Platform.MAC);
        handle(Platform.WIN);
    }
    private static void handle(Platform platform) {
        GuiFactory factory = FactoryProducer.getFactory(platform);
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        button.pain();
        checkbox.pain();
        System.out.println();
    }
}
