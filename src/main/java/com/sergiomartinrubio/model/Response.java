package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "ok")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Message.class, name = "true"),
        @JsonSubTypes.Type(value = ErrorResponse.class, name = "false")
})
public class Response {
    private boolean ok;

    public Response() {
    }

    public Response(boolean ok, Message result) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return ok == response.ok;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ok);
    }

    @Override
    public String toString() {
        return "Response{" +
                "ok=" + ok +
                '}';
    }
}
