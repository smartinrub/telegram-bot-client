package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TelegramBotClientTest {

    private TelegramBotClient telegramBotClient = new TelegramBotClient("token");

    @Test
    void shouldSendMessage() {
        // WHEN
        Message message = telegramBotClient.sendMessage("chat-id", "message");

        // THEN
        User from = new User(1881024015L, true, "java", "cool_java_bot");
        Chat chat = new Chat(-489903905L, "Java", ChatType.GROUP, true);
        assertThat(message).isEqualTo(new Message(9, from, chat, 1626859986L, "any text"));
    }

}
