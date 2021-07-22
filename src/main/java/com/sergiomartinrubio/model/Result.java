package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Result {

    @JsonProperty("message_id")
    private long messageId;

    private User from;

    private Chat chat;


    private long date;

    private String text;

    public Result() {
    }

    public Result(long messageId, User from, Chat chat, long date, String text) {
        this.messageId = messageId;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
    }

    public long getMessageId() {
        return messageId;
    }

    public User getFrom() {
        return from;
    }

    public Chat getChat() {
        return chat;
    }

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result message1 = (Result) o;
        return messageId == message1.messageId && date == message1.date && Objects.equals(from, message1.from) && Objects.equals(chat, message1.chat) && Objects.equals(text, message1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, from, chat, date, text);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", from=" + from +
                ", chat=" + chat +
                ", date=" + date +
                ", message='" + text + '\'' +
                '}';
    }
}
