package com.atyeti;

public class RemoteControl {
    private static Device connectDevice;

    public static void connectToDevice(Device device){
        connectDevice=device;
    }

    public void pressPowerOn(){
        connectDevice.turnOn();
    }

    public void pressPowerOff(){
        connectDevice.turnOff();
    }

}
