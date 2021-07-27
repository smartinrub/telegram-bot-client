package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sergiomartinrubio.http.BotMessageSerializer;
import lombok.Value;

@Value
@JsonSerialize(using = BotMessageSerializer.class)
public class BotMessage {
    long chatId;
    String text;

    public String toJson(ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
