package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ResponseMessage {

    @JsonProperty("message_id")
    private long messageId;

    private User from;

    private Chat chat;


    private long date;

    private String text;

    public ResponseMessage() {
    }

    public ResponseMessage(long messageId, User from, Chat chat, long date, String text) {
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
        ResponseMessage responseMessage1 = (ResponseMessage) o;
        return messageId == responseMessage1.messageId && date == responseMessage1.date && Objects.equals(from, responseMessage1.from) && Objects.equals(chat, responseMessage1.chat) && Objects.equals(text, responseMessage1.text);
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
