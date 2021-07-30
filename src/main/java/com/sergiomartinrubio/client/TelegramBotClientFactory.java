package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.HttpRequestClientFactory;
import lombok.NoArgsConstructor;

/**
 * Factory class to create {@link TelegramBotClientImpl}
 *
 * @author Sergio Martin Rubio
 */
@NoArgsConstructor
public class TelegramBotClientFactory {

    /**
     * Creates {@link TelegramBotClientImpl} given a Telegram Bot Token
     *
     * @param botToken the Telegram bot token
     * @return the {@link TelegramBotClient}
     */
    public static TelegramBotClient createClient(String botToken) {
        return new TelegramBotClientImpl(HttpRequestClientFactory.create(botToken));
    }
}
