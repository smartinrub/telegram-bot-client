package com.sergiomartinrubio.exception;

public class TelegramException extends RuntimeException {
    private static final String MESSAGE = "An error occurred while talking to the Telegram API for method [%s]. " +
            "Error code: [%s] Description: [%s]";

    public TelegramException(String code, String description, String path) {
        super(String.format(MESSAGE, path, code, description));
    }
}
