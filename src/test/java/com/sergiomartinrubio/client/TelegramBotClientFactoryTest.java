package com.sergiomartinrubio.client;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TelegramBotClientFactoryTest {

    @Test
    void shouldCreateTelegramBotClient() {
        // WHEN
        TelegramBotClient telegramBotClientFactory = TelegramBotClientFactory.createClient("my-token");

        // THEN
        assertThat(telegramBotClientFactory).isNotNull();
        assertThat(telegramBotClientFactory).isInstanceOf(TelegramBotClient.class);
    }

}
