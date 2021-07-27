package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sergiomartinrubio.http.BotMessageSerializer;
import lombok.Value;

@Value
public class BotMessage {
    long chatId;
    String text;

    public String toJson(ObjectMapper mapper, BotMessageSerializer serializer) {
        // TODO: move object mapper and module to separate class
        SimpleModule module = new SimpleModule();
        module.addSerializer(serializer);

        mapper.registerModule(module);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
