package com.sergiomartinrubio.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.http.model.RequestBody;
import com.sergiomartinrubio.model.Response;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class ClientHttpRequest {
    private static final String APPLICATION_JSON_MEDIA_TYPE = "application/json";
    private static final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient httpClient;
    private final String baseUrl;

    public Response execute(String path, HttpMethod method, RequestBody requestBody) {
        String jsonRequestBody = buildJsonRequestBody(requestBody);
        HttpRequest request = buildHttpRequest(path, method, jsonRequestBody);

        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // TODO: Handle different types of error response like 401, 400 or 404. Map to exceptions.
            //  i.e. InvalidRequestException, AuthenticationException, TelegramException (Display a very generic error to the user)
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

    private String buildJsonRequestBody(RequestBody requestBody) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new BotMessageSerializer(BotMessage.class));

        mapper.registerModule(module);
        try {
            return mapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
