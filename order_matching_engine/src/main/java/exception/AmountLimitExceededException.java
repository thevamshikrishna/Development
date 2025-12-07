package exception;

public class AmountLimitExceededException extends InvalidOrderException {
    public AmountLimitExceededException(String message) {
        super(message);
    }
}
