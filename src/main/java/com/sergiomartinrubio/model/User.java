package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class represents a user (bot) returned by the Telegram API
 *
 * @author Sergio Martin Rubio
 * @see <a href="https://core.telegram.org/bots/api#user">Telegram Returned User Doc</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class User implements Result {
    private long id;

    @JsonProperty("is_bot")
    private Boolean isBot;

    @JsonProperty("first_name")
    private String firstName;

    private String username;

    @JsonProperty("can_join_groups")
    private Boolean canJoinGroups;

    @JsonProperty("can_read_all_group_messages")
    private Boolean canReadAllGroupMessages;

    @JsonProperty("supports_inline_queries")
    private Boolean supportsInlineQueries;

    public Optional<Boolean> getCanJoinGroups() {
        return Optional.ofNullable(canJoinGroups);
    }

    public Optional<Boolean> getCanReadAllGroupMessages() {
        return Optional.ofNullable(canReadAllGroupMessages);
    }

    public Optional<Boolean> getSupportsInlineQueries() {
        return Optional.ofNullable(supportsInlineQueries);
    }
}
