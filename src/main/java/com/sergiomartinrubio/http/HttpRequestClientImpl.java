package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.http.model.RequestBody;
import com.sergiomartinrubio.model.Response;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublisher;
import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

/**
 * Implementation of {@link HttpRequestClient}.
 *
 * @author Sergio Martin Rubio
 */
@RequiredArgsConstructor
public class HttpRequestClientImpl implements HttpRequestClient {
    private static final String APPLICATION_JSON_MEDIA_TYPE = "application/json";

    private final HttpClient httpClient;
    private final String baseUrl;
    private final ObjectMapper mapper;

    @Override
    public Response execute(String path, HttpMethod method, RequestBody requestBody) {
        String jsonRequestBody = requestBody.toJson(mapper);
        HttpRequest request = getHttpRequest(path, method, jsonRequestBody);
        return send(request);
    }

    @Override
    public Response execute(String path, HttpMethod method) {
        HttpRequest request = getHttpRequest(path, method);
        return send(request);
    }

    private HttpRequest getHttpRequest(String path, HttpMethod method, String jsonRequestBody) {
        return buildHttpRequest(path, method, BodyPublishers.ofString(jsonRequestBody));
    }

    private HttpRequest getHttpRequest(String path, HttpMethod method) {
        return buildHttpRequest(path, method, BodyPublishers.noBody());
    }

    private HttpRequest buildHttpRequest(String path, HttpMethod method, BodyPublisher bodyPublisher) {
        return newBuilder(URI.create(baseUrl + path))
                .header("Content-Type", APPLICATION_JSON_MEDIA_TYPE)
                .GET()
                .method(method.name(), bodyPublisher)
                .build();
    }

    private Response send(HttpRequest request) {
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(httpResponse.body(), Response.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
