package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * This class represents a message returned by the Telegram API.
 *
 * @author Sergio Martin Rubio
 * @see <a href="https://core.telegram.org/bots/api#message">Telegram Returned Message Doc</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Result {
    /**
     * Message ID of the new message.
     */
    @JsonProperty("message_id")
    private long messageId;

    /**
     * Information about the sender of the message.
     */
    private User from;

    /**
     * Message creation timestamp in epoch format.
     */
    private long date;

    /**
     * Information about the destination chat of the message.
     */
    private Chat chat;

    /**
     * Optional. For forwarded messages, sender of the original message.
     */
    @JsonProperty("forward_from")
    private User forwardFrom;

    /**
     * Optional. Message forwarded timestamp in epoch format.
     */
    @JsonProperty("forward_date")
    private Long forwardDate;

    /**
     * Text included in the message.
     */
    private String text;

    public Optional<User> getForwardFrom() {
        return Optional.ofNullable(forwardFrom);
    }

    public Optional<Long> getForwardDate() {
        return Optional.ofNullable(forwardDate);
    }
}
