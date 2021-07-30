package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sergiomartinrubio.client.TelegramBotClient;
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
    /**
     * ID of the user.
     */
    private long id;

    /**
     * True, if this user is a bot.
     */
    @JsonProperty("is_bot")
    private Boolean isBot;

    /**
     * Bot's first name.
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. User's or bot's username.
     */
    private String username;

    /**
     * Optional. True, if the bot can be invited to groups. Returned only in {@link TelegramBotClient#getMe()}.
     */
    @JsonProperty("can_join_groups")
    private Boolean canJoinGroups;

    /**
     * Optional. True, if privacy mode is disabled for the bot. Returned only in {@link TelegramBotClient#getMe()}.
     */
    @JsonProperty("can_read_all_group_messages")
    private Boolean canReadAllGroupMessages;

    /**
     * Optional. True, if the bot supports inline queries. Returned only in {@link TelegramBotClient#getMe()}.
     */
    @JsonProperty("supports_inline_queries")
    private Boolean supportsInlineQueries;

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

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
