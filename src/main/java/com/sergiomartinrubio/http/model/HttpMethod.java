package com.sergiomartinrubio.http.model;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

    static {
        HttpMethod[] httpMethods = values();

        for (HttpMethod httpMethod : httpMethods) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    public static HttpMethod resolve(String method) {
        return method != null ? mappings.get(method) : null;
    }
}
