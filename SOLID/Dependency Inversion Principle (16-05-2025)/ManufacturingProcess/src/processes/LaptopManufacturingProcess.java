package processes;

public class LaptopManufacturingProcess extends GeneraManufacturingProcess {

    public LaptopManufacturingProcess(String name) {
        super(name);
    }

    @Override
    protected void assembleDevice() {
        System.out.println("Laptop Assembled...");
    }

    @Override
    protected void testDevice() {
        System.out.println("Laptop Testes...");
    }

    @Override
    protected void packageDevice() {
        System.out.println("Laptop Packed...");
    }

    @Override
    protected void storeDevice() {
        System.out.println("Laptop Stored");
    }
}