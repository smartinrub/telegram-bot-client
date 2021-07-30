package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sergiomartinrubio.http.serializer.ForwardMessageSerializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = ForwardMessageSerializer.class)
public class ForwardMessage extends RequestBody {
    long chatId;
    long fromChatId;
    long messageId;
}
