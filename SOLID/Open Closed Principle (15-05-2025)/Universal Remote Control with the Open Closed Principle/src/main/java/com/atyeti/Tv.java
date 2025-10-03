package com.atyeti;

public class Tv implements Device{
    @Override
    public void turnOff() {
        System.out.println("Tv Turned Off!");
    }

    @Override
    public void turnOn() {
        System.out.println("Tv Turned On!");
    }
}
