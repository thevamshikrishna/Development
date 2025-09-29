package com.atyeti;

public class SoundSystem implements Device{
    @Override
    public void turnOff() {
        System.out.println("SS Turned Off!");
    }

    @Override
    public void turnOn() {
        System.out.println("SS Turned On!");
    }
}
