package com.sergiomartinrubio.exception;

public class TelegramException extends RuntimeException {
    private static final String MESSAGE = "An error occurred while talking to the Telegram API. " +
            "Error code: [%s] Description: [%s]";

    public TelegramException(String message) {
        super(message);
    }

    public TelegramException(String code, String description) {
        super(String.format(MESSAGE, code, description));
    }
}
