package processes;

public class SmartPhoneManufacturingProcess extends GeneraManufacturingProcess {


    public SmartPhoneManufacturingProcess(String name) {
        super(name);
    }


    @Override
    protected void assembleDevice() {
        System.out.println("SmartPhone Assembled...");
    }

    @Override
    protected void testDevice() {
        System.out.println("SmartPhone Testes...");
    }

    @Override
    protected void packageDevice() {
        System.out.println("SmartPhone Packed...");
    }

    @Override
    protected void storeDevice() {
        System.out.println("SmartPhone Stored");
    }
}