package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.ResponseMessage;

/**
 * Client for interacting with Telegram Bots.
 *
 * @author Sergio Martin Rubio
 */
public interface TelegramBotClient {

    /**
     * Sends a message to a particular chat
     * @param chatId the target chat for the bot message
     * @param message the message as {@link String}
     * @return message response if successful
     */
    ResponseMessage sendMessage(long chatId, String message);
}
