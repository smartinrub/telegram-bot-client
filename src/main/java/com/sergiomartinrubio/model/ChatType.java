package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the different types of chats. Type of chat, can be either “private”,
 * “group”, “supergroup” or “channel”.
 *
 * @author Sergio Martin Rubio
 */
public enum ChatType {
    @JsonProperty("private")
    PRIVATE,

    @JsonProperty("group")
    GROUP,

    @JsonProperty("supergroup")
    SUPERGROUP,

    @JsonProperty("channel")
    CHANNEL
}
