package com.sergiomartinrubio.exception;

public class InvalidRequestException extends TelegramException {
    private static final String MESSAGE = "Invalid request with reason [%s]";

    public InvalidRequestException(String reasion) {
        super(String.format(MESSAGE, reasion));
    }

}
