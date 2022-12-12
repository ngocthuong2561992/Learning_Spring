package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class WinFactory extends GuiFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}
