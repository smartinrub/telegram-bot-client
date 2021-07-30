package com.sergiomartinrubio.http.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ForwardMessage extends RequestBody {
    long chatId;
    long fromChatId;
    long messageId;
}
