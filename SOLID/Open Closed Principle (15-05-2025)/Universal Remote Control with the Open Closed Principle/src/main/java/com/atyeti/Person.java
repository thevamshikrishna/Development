package com.atyeti;

public class Person {
    public static void main(String[] args) {
        RemoteControl rc=new RemoteControl();

        Tv tv=new Tv();
        SoundSystem ss=new SoundSystem();
        Projector pj=new Projector();

        RemoteControl.connectToDevice(tv);
        rc.pressPowerOn();
        rc.pressPowerOff();
    }
}