package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Response;
import lombok.RequiredArgsConstructor;

import static com.sergiomartinrubio.client.utils.Methods.GET_ME;
import static com.sergiomartinrubio.client.utils.Methods.SEND_MESSAGE;
import static com.sergiomartinrubio.http.model.HttpMethod.GET;
import static com.sergiomartinrubio.http.model.HttpMethod.POST;

@RequiredArgsConstructor
class TelegramBotClientImpl implements TelegramBotClient {

    private final ClientHttpRequest clientHttpRequestImpl;
    private final ErrorResponseHandler errorResponseHandler;

    @Override
    public Response sendMessage(long chatId, String message) {
        Response response = clientHttpRequestImpl.execute(SEND_MESSAGE, POST, new BotMessage(chatId, message));
        if (response instanceof ErrorResponse) {
            errorResponseHandler.handle(response, SEND_MESSAGE);
        }
        return response;
    }

    @Override
    public Response getMe() {
        Response response = clientHttpRequestImpl.execute(GET_ME, GET);
        if (response instanceof ErrorResponse) {
            errorResponseHandler.handle(response, GET_ME);
        }
        return response;
    }
}
