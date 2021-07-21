package com.sergiomartinrubio.http;

import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.http.model.RequestBody;
import com.sergiomartinrubio.model.ResponseMessage;

public class ClientHttpRequest {

    private final String baseUrl;

    public ClientHttpRequest(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ResponseMessage doExecute(String path, HttpMethod method, RequestBody requestBody) {
        return null;
    }
}
