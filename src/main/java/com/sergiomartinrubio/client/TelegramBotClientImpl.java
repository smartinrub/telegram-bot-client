package com.sergiomartinrubio.client;

import com.sergiomartinrubio.exception.TelegramException;
import com.sergiomartinrubio.http.HttpRequestClient;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.ForwardMessage;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.SuccessfulResponse;
import com.sergiomartinrubio.model.User;
import lombok.RequiredArgsConstructor;

import static com.sergiomartinrubio.client.utils.Methods.FORWARD_MESSAGE_PATH;
import static com.sergiomartinrubio.client.utils.Methods.GET_ME_PATH;
import static com.sergiomartinrubio.client.utils.Methods.SEND_MESSAGE_PATH;
import static com.sergiomartinrubio.http.model.HttpMethod.GET;
import static com.sergiomartinrubio.http.model.HttpMethod.POST;

@RequiredArgsConstructor
class TelegramBotClientImpl implements TelegramBotClient {

    private final HttpRequestClient httpRequestClientImpl;

    @Override
    public Message sendMessage(long chatId, String message) {
        var botMessage = BotMessage.builder()
                .chatId(chatId)
                .text(message)
                .build();
        Response response = httpRequestClientImpl.execute(SEND_MESSAGE_PATH, POST, botMessage);

        if (response instanceof ErrorResponse) {
            var errorResponse = (ErrorResponse) response;
            throw new TelegramException(errorResponse.getErrorCode(), errorResponse.getDescription(), SEND_MESSAGE_PATH);
        }

        var successfulResponse = (SuccessfulResponse) response;
        return (Message) successfulResponse.getResult();
    }

    @Override
    public User getMe() {
        Response response = httpRequestClientImpl.execute(GET_ME_PATH, GET);

        if (response instanceof ErrorResponse) {
            var errorResponse = (ErrorResponse) response;
            throw new TelegramException(errorResponse.getErrorCode(), errorResponse.getDescription(), GET_ME_PATH);
        }

        var successfulResponse = (SuccessfulResponse) response;
        return (User) successfulResponse.getResult();
    }

    @Override
    public Message forwardMessage(long chatId, long fromChatId, long messageId) {
        var forwardMessage = ForwardMessage.builder()
                .chatId(chatId)
                .fromChatId(fromChatId)
                .messageId(messageId)
                .build();
        Response response = httpRequestClientImpl.execute(FORWARD_MESSAGE_PATH, GET, forwardMessage);

        if (response instanceof ErrorResponse) {
            var errorResponse = (ErrorResponse) response;
            throw new TelegramException(
                    errorResponse.getErrorCode(),
                    errorResponse.getDescription(),
                    FORWARD_MESSAGE_PATH
            );
        }

        var successfulResponse = (SuccessfulResponse) response;
        return (Message) successfulResponse.getResult();
    }
}
