package com.sergiomartinrubio.http;

import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.http.model.RequestBody;
import com.sergiomartinrubio.model.Response;

public interface HttpRequestClient {
    Response execute(String path, HttpMethod method, RequestBody requestBody);

    Response execute(String path, HttpMethod method);
}
