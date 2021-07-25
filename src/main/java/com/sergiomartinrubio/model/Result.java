package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @JsonProperty("message_id")
    private long messageId;

    private User from;
    private Chat chat;
    private long date;
    private String text;
}
