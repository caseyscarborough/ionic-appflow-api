package ionic.appflow;

public class AppflowException extends RuntimeException {

    public AppflowException(String message) {
        super(message);
    }

    public AppflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
