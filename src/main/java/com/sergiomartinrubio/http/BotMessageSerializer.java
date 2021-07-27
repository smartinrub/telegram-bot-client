package com.sergiomartinrubio.http;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sergiomartinrubio.http.model.BotMessage;

import java.io.IOException;

public class BotMessageSerializer extends JsonSerializer<BotMessage> {

    @Override
    public void serialize(BotMessage botMessage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("chat_id", botMessage.getChatId());
        jsonGenerator.writeStringField("text", String.valueOf(botMessage.getText()));
        jsonGenerator.writeEndObject();
    }
}
