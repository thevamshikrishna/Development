public class AccountsReceivable {


    private Accounting transactionObject;

    public AccountsReceivable(CustomerTransaction transactionObject1) {
       transactionObject = transactionObject1;
    }


    /*
    Using above code we get

    Abstraction

Dependency Injection

Loose Coupling

Open/Closed Principle from SOLID (open for extension, closed for modification)


     */

    public void postPayment(){
        transactionObject.chargeCustomer();
    }

    public void sendInvoice(){
        transactionObject.prepareInvoice();
        //sends the invoice
    }

}
