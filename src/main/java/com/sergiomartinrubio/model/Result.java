package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = Message.class)
@JsonSubTypes({@JsonSubTypes.Type(UserResponse.class)})
public class Result {

    @JsonProperty("message_id")
    private long messageId;

    private User from;
    private Chat chat;
    private long date;
    private String text;
}
