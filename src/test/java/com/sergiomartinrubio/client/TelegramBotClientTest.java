package com.sergiomartinrubio.client;

import com.sergiomartinrubio.model.Chat;
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
        assertThat(message).isEqualTo(new Message(9, new User(), new Chat(), 1626859986L, "any text"));
    }

}
