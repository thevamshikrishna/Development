class PaymentProcessor {
    private PaymentStrategy strategy;

    // Inject the strategy via constructor
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(double amount) {
        strategy.pay(amount);
    }
}
