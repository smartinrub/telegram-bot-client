package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;

import java.net.http.HttpClient;

public class TelegramBotClientFactory {

    private static final String BASE_URL = "https://api.telegram.org/bot";

    private TelegramBotClientFactory() {
    }

    public static TelegramBotClient createClient(String botToken) {
        return new TelegramBotClientImpl(new ClientHttpRequest(HttpClient.newHttpClient(), BASE_URL + botToken));
    }
}
