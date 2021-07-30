package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message extends Result {
    @JsonProperty("message_id")
    private long messageId;

    private User from;
    private long date;
    private Chat chat;

    @JsonProperty("forward_from")
    private User forwardFrom;

    @JsonProperty("forward_date")
    private Long forwardDate;

    private String text;

    public Optional<User> getForwardFrom() {
        return Optional.ofNullable(forwardFrom);
    }

    public Optional<Long> getForwardDate() {
        return Optional.ofNullable(forwardDate);
    }
}
