package devices;

public class Tv implements RemoteControl {
    @Override
    public void turningOn() {
        System.out.println("Tv Turning On");
    }

    @Override
    public void turningOff() {
        System.out.println("Tv Turning Off");
    }


}
