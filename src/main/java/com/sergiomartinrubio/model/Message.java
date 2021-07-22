package com.sergiomartinrubio.model;

import java.util.Objects;

public class Message extends Response {

    private Result result;

    public Message() {
    }

    public Message(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Message message = (Message) o;
        return Objects.equals(result, message.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result);
    }

    @Override
    public String toString() {
        return "Message{" +
                "result=" + result +
                '}';
    }
}
