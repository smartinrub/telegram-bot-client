package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.User;

/**
 * Client for interacting with Telegram Bots.
 *
 * @author Sergio Martin Rubio
 */
public class TelegramBotClient {

    private final String botToken;

    public TelegramBotClient(String botToken) {
        this.botToken = botToken;
    }


    public Message sendMessage(String chatId, String message) {

        return new Message(9, new User(), new Chat(), 1626859986L, "any text");
    }
}
