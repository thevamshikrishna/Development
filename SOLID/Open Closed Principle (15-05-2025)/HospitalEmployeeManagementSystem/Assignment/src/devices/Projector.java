package devices;

public class Projector implements RemoteControl {
    @Override
    public void turningOn() {
        System.out.println("Projector Turning On");
    }

    @Override
    public void turningOff() {
        System.out.println("Projector Turning Off");
    }
}
