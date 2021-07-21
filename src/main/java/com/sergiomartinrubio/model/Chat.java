package com.sergiomartinrubio.model;

import java.util.Objects;

public class Chat {
    private final long id;
    private final String title;
    private final ChatType type;
    private final boolean allowMembersAreAdministrators;

    public Chat(long id, String title, ChatType type, boolean allowMembersAreAdministrators) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.allowMembersAreAdministrators = allowMembersAreAdministrators;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ChatType getType() {
        return type;
    }

    public boolean isAllowMembersAreAdministrators() {
        return allowMembersAreAdministrators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id && allowMembersAreAdministrators == chat.allowMembersAreAdministrators && Objects.equals(title, chat.title) && type == chat.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, allowMembersAreAdministrators);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", allowMembersAreAdministrators=" + allowMembersAreAdministrators +
                '}';
    }
}
