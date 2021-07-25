package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Response;

import static com.sergiomartinrubio.http.model.HttpMethod.POST;

class TelegramBotClientImpl implements TelegramBotClient {

    private final ClientHttpRequest clientHttpRequest;
    private final ErrorResponseHandler errorResponseHandler;

    TelegramBotClientImpl(ClientHttpRequest clientHttpRequest, ErrorResponseHandler errorResponseHandler) {
        this.clientHttpRequest = clientHttpRequest;
        this.errorResponseHandler = errorResponseHandler;
    }

    @Override
    public Response sendMessage(long chatId, String message) {
        String path = "/sendMessage";
        Response response = clientHttpRequest.execute(path, POST, new BotMessage(chatId, message));
        if (response instanceof ErrorResponse) {
            errorResponseHandler.handle(response, path);
        }
        return response;
    }
}
