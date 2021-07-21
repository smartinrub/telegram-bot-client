package com.sergiomartinrubio.model;

import java.util.Objects;

public class ResponseMessage {
    private final long messageId;
    private final User from;
    private final Chat chat;
    private final long date;
    private final String message;

    public ResponseMessage(long messageId, User from, Chat chat, long date, String message) {
        this.messageId = messageId;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseMessage responseMessage1 = (ResponseMessage) o;
        return messageId == responseMessage1.messageId && date == responseMessage1.date && Objects.equals(from, responseMessage1.from) && Objects.equals(chat, responseMessage1.chat) && Objects.equals(message, responseMessage1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, from, chat, date, message);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", from=" + from +
                ", chat=" + chat +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
