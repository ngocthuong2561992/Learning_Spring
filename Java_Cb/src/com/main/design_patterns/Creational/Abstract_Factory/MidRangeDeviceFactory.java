package com.main.design_patterns.Creational.Abstract_Factory;

public class MidRangeDeviceFactory extends ElectronicDeviceAbstractFactory {
    @Override
    Laptop createLaptop() {
        return new MidRangeLaptop();
    }

    @Override
    Phone createPhone() {
        return new MidRangePhone();
    }
}
