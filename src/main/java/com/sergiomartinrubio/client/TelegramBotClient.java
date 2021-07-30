package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Message;
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
     * @param chatId  the target chat for the bot message
     * @param message the message as {@link String}
     * @return information about the message sent
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Telegram SendMessage Doc</a>
     * @see <a href="https://core.telegram.org/bots/api#message">Telegram Returned Message Doc</a>
     */
    Message sendMessage(long chatId, String message);

    /**
     * Returns basic information about the bot
     *
     * @return the bot in form of a {@link User}
     * @see <a href="https://core.telegram.org/bots/api#getme">Telegram GetMe Doc</a>
     * @see <a href="https://core.telegram.org/bots/api#user">Telegram Returned User Doc</a>
     */
    User getMe();

    /**
     * Forwards message from one chat to another
     *
     * @param chatId     the destination chat for the message to be forwarded
     * @param fromChatId the source chat where the message to be forwarded resides
     * @param messageId  the target message to be forwarded
     * @return information about the message forwarded
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Telegram ForwardMessage Doc</a>
     * @see <a href="https://core.telegram.org/bots/api#message">Telegram Returned Message Doc</a>
     */
    Message forwardMessage(long chatId, long fromChatId, long messageId);
}
