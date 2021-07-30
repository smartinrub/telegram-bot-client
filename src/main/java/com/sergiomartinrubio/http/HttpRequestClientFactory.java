package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;

/**
 * Factory class for creating a {@link HttpRequestClientImpl}.
 *
 * @author Sergio Martin Rubio
 */
public class HttpRequestClientFactory {
    private static final String BASE_URL = "https://api.telegram.org/bot";

    /**
     * Creates {@link HttpRequestClientImpl} given a Telegram Bot Token.
     *
     * @param botToken the Telegram Bot Token
     * @return the {@link HttpRequestClient}
     */
    public static HttpRequestClient create(String botToken) {
        return new HttpRequestClientImpl(HttpClient.newHttpClient(), BASE_URL + botToken, new ObjectMapper());
    }
}
