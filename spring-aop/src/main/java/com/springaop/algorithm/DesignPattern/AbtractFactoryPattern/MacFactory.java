package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class MacFactory extends GuiFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
