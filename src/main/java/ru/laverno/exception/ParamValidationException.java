package ru.laverno.exception;

public class ParamValidationException extends RuntimeException {

    public ParamValidationException(String message) {
        super(message);
    }
}
