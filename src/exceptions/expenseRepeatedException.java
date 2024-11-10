package exceptions;

public class expenseRepeatedException extends RuntimeException {
    public expenseRepeatedException(String message) {
        super(message);
    }
}
