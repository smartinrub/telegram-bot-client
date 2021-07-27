package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.Response;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class ClientHttpRequestImpl implements ClientHttpRequest {
    private static final String APPLICATION_JSON_MEDIA_TYPE = "application/json";

    private final HttpClient httpClient;
    private final String baseUrl;
    private final ObjectMapper mapper;

    @Override
    public Response execute(String path, HttpMethod method, BotMessage botMessage) {
        String jsonRequestBody = botMessage.toJson(mapper);
        HttpRequest request = buildHttpRequest(path, method, jsonRequestBody);

        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(httpResponse.body(), Response.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest buildHttpRequest(String path, HttpMethod method, String jsonRequestBody) {
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
                .header("Content-Type", APPLICATION_JSON_MEDIA_TYPE)
                .method(method.name(), HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();
    }

}
