package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sergiomartinrubio.http.serializer.ForwardMessageSerializer;
import lombok.Builder;
import lombok.Value;

/**
 * This class represents the request body for forwarding a message.
 *
 * @author Sergio Martin Rubio
 */
@Value
@Builder
@JsonSerialize(using = ForwardMessageSerializer.class)
public class ForwardMessage implements RequestBody {
    /**
     * Chat ID of the destination chat.
     */
    long chatId;

    /**
     * Chat ID of the source chat.
     */
    long fromChatId;

    /**
     * Message ID of the message to be forwarded from the source chat.
     */
    long messageId;
}
