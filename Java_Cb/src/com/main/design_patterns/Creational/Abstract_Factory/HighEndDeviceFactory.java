package com.main.design_patterns.Creational.Abstract_Factory;

public class HighEndDeviceFactory extends ElectronicDeviceAbstractFactory {
    @Override
    Laptop createLaptop() {
        return new HighEndLaptop();
    }

    @Override
    Phone createPhone() {
        return new HighEndPhone();
    }
}
