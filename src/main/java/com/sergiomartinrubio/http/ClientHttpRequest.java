package com.sergiomartinrubio.http;

import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.Response;

public interface ClientHttpRequest {
    Response execute(String path, HttpMethod method, BotMessage botMessage);
}
