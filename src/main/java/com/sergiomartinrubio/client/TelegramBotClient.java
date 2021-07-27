package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Response;

/**
 * Client for interacting with Telegram Bots.
 *
 * @author Sergio Martin Rubio
 */
public interface TelegramBotClient {

    /**
     * Sends a message to a particular chat
     *
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Telegram SendMessage Doc</a>
     * @see <a href="https://core.telegram.org/bots/api#message">Telegram Returned Message Doc</a>
     *
     * @param chatId the target chat for the bot message
     * @param message the message as {@link String}
     * @return message response if successful
     */
    Response sendMessage(long chatId, String message);

    Response getMe();
}
