package exception;

public class DuplicateOrderException extends OrderException {
    public DuplicateOrderException(String message) {
        super(message);
    }
}
