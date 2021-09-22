package ionic.appflow.exception;

public class AppflowHttpException extends AppflowException {
    public AppflowHttpException(int code, String url) {
        super(String.format("An HTTP error %d occurred while calling URL %s", code, url));
    }
}
