package se.coredev.jaxrs.model;

public final class BadUserInputException extends RuntimeException {

    public BadUserInputException() {
    }

    public BadUserInputException(String message) {
        super(message);
    }
}
