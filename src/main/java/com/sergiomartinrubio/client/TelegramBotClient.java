package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.User;

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
    Message sendMessage(long chatId, String message);

    /**
     * Returns basic information about the bot
     *
     * @see <a href="https://core.telegram.org/bots/api#getme">Telegram GetMe Doc</a>
     * @see <a href="https://core.telegram.org/bots/api#user">Telegram Returned User Doc</a>
     *
     * @return the bot in form of a {@link User}
     */
    User getMe();

    Message forwardMessage(long chatId, long fromChatId, long messageId);
}
