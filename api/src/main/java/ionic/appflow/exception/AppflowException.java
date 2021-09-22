package ionic.appflow.exception;

public class AppflowException extends RuntimeException {

    public AppflowException(String message) {
        super(message);
    }

    public AppflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
