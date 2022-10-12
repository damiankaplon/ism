package pl.damiankaplon.domain;

public class ISMException extends Exception {
    public ISMException(String message) {
        super(message);
    }

    public ISMException(String message, Throwable cause) {
        super(message, cause);
    }
}
