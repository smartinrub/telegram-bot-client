package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.net.http.HttpClient;

@NoArgsConstructor
public class HttpRequestClientFactory {
    private static final String BASE_URL = "https://api.telegram.org/bot";

    public static HttpRequestClient create(String botToken) {
        return new HttpRequestClientImpl(HttpClient.newHttpClient(), BASE_URL + botToken, new ObjectMapper());
    }
}
