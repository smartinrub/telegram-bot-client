package com.sergiomartinrubio.exception;

public class ResourceNotFoundException extends TelegramException {
    private static final String MESSAGE = "Invalid resource [%s] with reason [%s]";

    public ResourceNotFoundException(String resource, String reason) {
        super(String.format(MESSAGE, resource, reason));
    }
}
