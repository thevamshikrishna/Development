package exception;

public class InvalidOrderException extends OrderException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
