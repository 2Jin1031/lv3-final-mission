package finalmission.global.exception;

public class BadRequestException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "bad request";

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        this(DEFAULT_MESSAGE);
    }
}
