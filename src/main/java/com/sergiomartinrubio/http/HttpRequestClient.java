package com.sergiomartinrubio.http;

import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.http.model.RequestBody;
import com.sergiomartinrubio.model.Response;

/**
 * Client for executing HTTP requests
 *
 * @author Sergio Martin Rubio
 */
public interface HttpRequestClient {
    /**
     * Makes a HTTP request
     *
     * @param path        the path for the request
     * @param method      the HTTP method of the request
     * @param requestBody the base class for creating a request body
     * @return a {@link Response}
     */
    Response execute(String path, HttpMethod method, RequestBody requestBody);

    /**
     * Makes a HTTP request
     *
     * @param path   the path for the request
     * @param method the HTTP method of the request
     * @return a {@link Response}
     */
    Response execute(String path, HttpMethod method);
}
