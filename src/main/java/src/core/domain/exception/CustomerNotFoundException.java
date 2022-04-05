package src.core.domain.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Customer Not Found";

    public CustomerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
