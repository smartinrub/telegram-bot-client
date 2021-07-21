package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
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

        User from = new User(1881024015L, true, "java", "cool_java_bot");
        Chat chat = new Chat(-489903905L, "Java", ChatType.GROUP, true);
        return new Message(9, from, chat, 1626859986L, "any text");
    }
}
