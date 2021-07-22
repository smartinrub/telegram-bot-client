package com.sergiomartinrubio.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.http.model.RequestBody;
import com.sergiomartinrubio.model.ResponseMessage;
import com.sergiomartinrubio.model.ResponseMessageWrapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttpRequest {

    private final HttpClient httpClient;
    private final String baseUrl;

    public ClientHttpRequest(HttpClient httpClient, String baseUrl) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
    }

    public ResponseMessage doExecute(String path, HttpMethod method, RequestBody requestBody) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new BotMessageSerializer(BotMessage.class));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        String jsonRequestBody;
        try {
            jsonRequestBody = mapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder(URI.create(baseUrl + path))
                .header("Content-Type", "application/json")
                .method(method.name(), HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper deserializerMapper = new ObjectMapper();
            ResponseMessageWrapper responseMessageWrapper = deserializerMapper.readValue(response.body(), ResponseMessageWrapper.class);
            return responseMessageWrapper.getResult();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
