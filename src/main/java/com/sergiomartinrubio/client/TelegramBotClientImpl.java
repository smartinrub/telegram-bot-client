package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Response;
import lombok.RequiredArgsConstructor;

import static com.sergiomartinrubio.http.model.HttpMethod.POST;

@RequiredArgsConstructor
class TelegramBotClientImpl implements TelegramBotClient {

    private final ClientHttpRequest clientHttpRequest;
    private final ErrorResponseHandler errorResponseHandler;

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
