package com.sergiomartinrubio.model;

import java.util.Objects;

public class User {
    private final long id;
    private final boolean isBot;
    private final String firstName;
    private final String username;

    public User(long id, boolean isBot, String firstName, String username) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public boolean isBot() {
        return isBot;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && isBot == user.isBot && Objects.equals(firstName, user.firstName) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isBot, firstName, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isBot=" + isBot +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
