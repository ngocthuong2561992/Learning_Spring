package com.springaop.algorithm.DesignPattern.AAA;

public class Computer {
    private String cpu;
    private String ram;

    public Computer(){}
    public Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public static class Builder {
        private String cpu;
        private String ram;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }
        public Computer build() {
            return new Computer(this);
        }
    }

}
