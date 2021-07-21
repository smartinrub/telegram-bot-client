package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.ResponseMessage;

/**
 * Client for interacting with Telegram Bots.
 *
 * @author Sergio Martin Rubio
 */
class TelegramBotClientImpl implements TelegramBotClient {

    private final ClientHttpRequest clientHttpRequest;

    TelegramBotClientImpl(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    @Override
    public ResponseMessage sendMessage(long chatId, String message) {
        return clientHttpRequest.doExecute("/sendMessage", HttpMethod.POST, new BotMessage(chatId, message));
    }
}
