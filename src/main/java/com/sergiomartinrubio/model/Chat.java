package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private long id;
    private String title;
    private ChatType type;

    @JsonProperty("all_members_are_administrators")
    private boolean allowMembersAreAdministrators;
}
