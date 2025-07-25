package com.atyeti;

public class Projector implements Device{
    @Override
    public void turnOff() {
        System.out.println("Projector Turned Off!");
    }

    @Override
    public void turnOn() {
        System.out.println("Projector Turned On!");
    }
}
