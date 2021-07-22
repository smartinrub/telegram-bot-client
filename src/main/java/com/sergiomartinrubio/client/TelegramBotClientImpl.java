package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.Response;

class TelegramBotClientImpl implements TelegramBotClient {

    private final ClientHttpRequest clientHttpRequest;

    TelegramBotClientImpl(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    @Override
    public Response sendMessage(long chatId, String message) {
        return clientHttpRequest.execute("/sendMessage", HttpMethod.POST, new BotMessage(chatId, message));
    }
}
