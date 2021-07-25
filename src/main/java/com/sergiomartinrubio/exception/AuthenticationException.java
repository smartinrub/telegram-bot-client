package com.sergiomartinrubio.exception;

public class AuthenticationException extends TelegramException {
    private static final String MESSAGE = "Authentication error with reason [%s]";

    public AuthenticationException(String reason) {
        super(String.format(MESSAGE, reason));
    }
}
