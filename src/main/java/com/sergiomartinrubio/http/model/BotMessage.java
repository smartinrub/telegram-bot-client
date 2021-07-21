package com.sergiomartinrubio.http.model;

import java.util.Objects;

public class BotMessage extends RequestBody {
    private final long chatId;
    private final String text;

    public BotMessage(long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public long getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotMessage that = (BotMessage) o;
        return chatId == that.chatId && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, text);
    }

    @Override
    public String toString() {
        return "BotMessage{" +
                "chatId=" + chatId +
                ", text='" + text + '\'' +
                '}';
    }
}
