package client;

import processes.GeneraManufacturingProcess;
import processes.LaptopManufacturingProcess;
import processes.SmartPhoneManufacturingProcess;

public class DeviceFactory {
    public static void main(String[] args) {
//        processes.GeneraManufacturingProcess g1=new LaptopManufacturingProcess("Laptop");
//        g1.assembleDevice();
//        g1.testDevice();
//        g1.packageDevice();
//        g1.storeDevice();
//
//        processes.GeneraManufacturingProcess g2=new SmartPhoneManufacturingProcess("SmartPhone");
//        g2.assembleDevice();
//        g2.testDevice();
//        g2.packageDevice();
//        g2.storeDevice();

        GeneraManufacturingProcess obj=new LaptopManufacturingProcess("laptop");
        obj.launchProcess();

        GeneraManufacturingProcess obj1=new SmartPhoneManufacturingProcess("SmartPhone");
        obj1.launchProcess();
    }
}