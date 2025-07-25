package devices;

public class SurroundSoundSystem implements RemoteControl {
    @Override
    public void turningOn() {
        System.out.println("SurroundSoundSystem Turning On");
    }

    @Override
    public void turningOff() {
        System.out.println("SurroundSoundSystem Turning Off");
    }
}
