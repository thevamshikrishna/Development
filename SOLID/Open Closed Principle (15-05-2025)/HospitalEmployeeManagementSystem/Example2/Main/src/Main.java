public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor1 = new PaymentProcessor(new CreditCardPayment());
        processor1.processPayment(100.0);

        PaymentProcessor processor2 = new PaymentProcessor(new PayPalPayment());
        processor2.processPayment(200.0);
    }
}
