package com.sergiomartinrubio.utils;

import lombok.SneakyThrows;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CustomHttpResponse implements HttpResponse<String> {

    private final String file;

    public CustomHttpResponse(String file) {
        this.file = file;
    }

    @Override
    public int statusCode() {
        return 0;
    }

    @Override
    public HttpRequest request() {
        return null;
    }

    @Override
    public Optional<HttpResponse<String>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @SneakyThrows
    @Override
    public String body() {
        return Files.readString(Path.of("src/test/resources/json/" + file), UTF_8);
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public HttpClient.Version version() {
        return null;
    }
}
