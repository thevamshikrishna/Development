package processes;

public abstract class GeneraManufacturingProcess {

     private String processName;

     public void launchProcess(){
         if(processName!=null  &!processName.isEmpty()){
         assembleDevice();testDevice();packageDevice();storeDevice();}
         else{
             System.out.println("No Process Name Was Specified");
         }
     }

     public GeneraManufacturingProcess(String name){
          this.processName=name;
     }

    protected abstract void assembleDevice();

    protected abstract void testDevice();

    protected abstract void packageDevice();

    protected abstract void storeDevice();

}
