package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sergiomartinrubio.http.BotMessageSerializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = BotMessageSerializer.class)
public class BotMessage extends RequestBody {
    long chatId;
    String text;
}
