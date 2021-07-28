package com.sergiomartinrubio.client;

import com.sergiomartinrubio.exception.TelegramException;
import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.SuccessfulResponse;
import com.sergiomartinrubio.model.User;
import lombok.RequiredArgsConstructor;

import static com.sergiomartinrubio.client.utils.Methods.GET_ME;
import static com.sergiomartinrubio.client.utils.Methods.SEND_MESSAGE;
import static com.sergiomartinrubio.http.model.HttpMethod.GET;
import static com.sergiomartinrubio.http.model.HttpMethod.POST;

@RequiredArgsConstructor
class TelegramBotClientImpl implements TelegramBotClient {

    private final ClientHttpRequest clientHttpRequestImpl;

    @Override
    public Message sendMessage(long chatId, String message) {
        Response response = clientHttpRequestImpl.execute(SEND_MESSAGE, POST, new BotMessage(chatId, message));

        if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            throw new TelegramException(errorResponse.getErrorCode(), errorResponse.getDescription(), SEND_MESSAGE);
        }

        SuccessfulResponse successfulResponse = (SuccessfulResponse) response;
        return (Message) successfulResponse.getResult();
    }

    @Override
    public User getMe() {
        Response response = clientHttpRequestImpl.execute(GET_ME, GET);

        if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            throw new TelegramException(errorResponse.getErrorCode(), errorResponse.getDescription(), GET_ME);
        }

        SuccessfulResponse successfulResponse = (SuccessfulResponse) response;
        return (User) successfulResponse.getResult();
    }
}
