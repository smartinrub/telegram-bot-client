package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sergiomartinrubio.http.serializer.BotMessageSerializer;
import lombok.Builder;
import lombok.Value;

/**
 * This class represents the request body for sending a new message.
 *
 * @author Sergio Martin Rubio
 */
@Value
@Builder
@JsonSerialize(using = BotMessageSerializer.class)
public class BotMessage implements RequestBody {
    /**
     * Chat ID of the destination chat.
     */
    long chatId;

    /**
     * Text of the message.
     */
    String text;
}
