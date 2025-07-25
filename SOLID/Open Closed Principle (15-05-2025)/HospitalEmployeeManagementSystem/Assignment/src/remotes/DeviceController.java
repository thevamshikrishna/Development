package remotes;

import devices.RemoteControl;

public class DeviceController {
    private RemoteControl remoteControl=null;
    private static final DeviceController INSTANCE =new DeviceController();

    public void connectToDevice(RemoteControl remoteControl){
        this.remoteControl=remoteControl;
        System.out.println("======Connected to: "+remoteControl);
    }

    private DeviceController(){}


    public static DeviceController getInstance(){
        return INSTANCE;
    }

    public void execute() {
        remoteControl.turningOn();
        remoteControl.turningOff();
    }
}

