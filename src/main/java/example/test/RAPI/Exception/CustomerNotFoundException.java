package example.test.RAPI.Exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
