package exception;

public class CountryNotAllowedException extends InvalidOrderException {
    public CountryNotAllowedException(String message) {
        super(message);
    }
}
