public class ReportGenerator {
    private Reporting transactionObject;

    public void generateReport(){
        System.out.println(transactionObject.getName()+
                " "+transactionObject.getProductBreakdown()+
                " "+transactionObject.getDate());
    }
}
