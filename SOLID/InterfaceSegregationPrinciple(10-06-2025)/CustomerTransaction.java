import java.util.Date;
import java.util.List;

public class CustomerTransaction implements Accounting, Reporting {

    private List<Product> products;

    private Customer customer;

    public CustomerTransaction(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
    }

    //methods for reporting
    @Override
    public String getName(){
     return customer.getName();
    }
    @Override
    public Date getDate(){
        return new Date();
    }

    @Override
    public String getProductBreakdown(){
        String reportList=null;
        for(Product product:products){
            reportList+=product.getProductName();
        }
        return reportList;
    }



    // Accounts receivable
    @Override
    public void prepareInvoice(){
        System.out.println("invoice prepared...");
    }
    @Override
    public void chargeCustomer(){
        System.out.println("charged the customer");
    }
}