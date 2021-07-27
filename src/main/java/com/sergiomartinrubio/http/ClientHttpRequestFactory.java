package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.net.http.HttpClient;

@NoArgsConstructor
public class ClientHttpRequestFactory {
    private static final String BASE_URL = "https://api.telegram.org/bot";

    public static ClientHttpRequest create(String botToken) {
        return new ClientHttpRequestImpl(HttpClient.newHttpClient(), BASE_URL + botToken, new ObjectMapper());
    }
}
