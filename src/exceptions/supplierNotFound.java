package exceptions;

public class supplierNotFound extends RuntimeException {
    public supplierNotFound(String message) {
        super(message);
    }
}
