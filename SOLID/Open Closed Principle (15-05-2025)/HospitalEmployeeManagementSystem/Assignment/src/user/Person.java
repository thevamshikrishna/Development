package user;

import remotes.DeviceController;
import devices.Projector;
import devices.SurroundSoundSystem;
import devices.Tv;

public class Person {
    public static void main(String[] args) {

//        DeviceController tv=new DeviceController(new Tv());
//        DeviceController projector=new DeviceController(new Projector());
//        DeviceController soundSystem=new DeviceController(new SurroundSoundSystem());
//
//        tv.execute();
//        projector.execute();
//        soundSystem.execute();

        Projector niceProjector = new Projector();
        Tv niceLargeScreenTV = new Tv();

        // The user's control
        DeviceController remoteControl = DeviceController.getInstance();

        remoteControl.connectToDevice(niceProjector);
        remoteControl.execute();

        remoteControl.connectToDevice(niceLargeScreenTV);
        remoteControl.execute();
    }
}

