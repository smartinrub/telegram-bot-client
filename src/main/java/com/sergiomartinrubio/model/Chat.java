package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class provides a Telegram chat details.
 *
 * @author Sergio Martin Rubio
 * @see <a href="https://core.telegram.org/bots/api#chat">Telegram Returned Chat Doc</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    /**
     * The ID of the chat.
     */
    private long id;

    /**
     * The title of the chat.
     */
    private String title;

    /**
     * The chat type.
     */
    private ChatType type;

    /**
     * If 'true' all members of the chat are administrator, otherwise all the member
     * are not administrators.
     */
    @JsonProperty("all_members_are_administrators")
    private boolean allowMembersAreAdministrators;
}
