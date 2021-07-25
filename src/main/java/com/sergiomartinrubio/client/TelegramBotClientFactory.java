package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;

import java.net.http.HttpClient;

/**
 * Factory class to create {@link TelegramBotClientImpl}
 *
 * @author Sergio Martin Rubio
 */
public class TelegramBotClientFactory {

    private static final String BASE_URL = "https://api.telegram.org/bot";

    private TelegramBotClientFactory() {
    }

    /**
     * Creates {@link TelegramBotClientImpl} given a Telegram Bot Token
     *
     * @param botToken the Telegram bot token
     * @return the {@link TelegramBotClient}
     */
    public static TelegramBotClient createClient(String botToken) {
        return new TelegramBotClientImpl(
                new ClientHttpRequest(HttpClient.newHttpClient(), BASE_URL + botToken),
                new ErrorResponseHandler()
        );
    }
}
