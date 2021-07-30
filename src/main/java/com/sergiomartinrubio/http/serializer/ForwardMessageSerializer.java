package com.sergiomartinrubio.http.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sergiomartinrubio.http.model.ForwardMessage;

import java.io.IOException;

/**
 * Serializer for converting a {@link ForwardMessage} to JSON.
 *
 * @author Sergio Martin Rubio
 */
public class ForwardMessageSerializer extends JsonSerializer<ForwardMessage> {
    @Override
    public void serialize(ForwardMessage message, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("chat_id", message.getChatId());
        jsonGenerator.writeNumberField("from_chat_id", message.getFromChatId());
        jsonGenerator.writeNumberField("message_id", message.getMessageId());
        jsonGenerator.writeEndObject();
    }
}
