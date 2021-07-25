package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "ok")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Message.class, name = "true"),
        @JsonSubTypes.Type(value = ErrorResponse.class, name = "false")
})
public class Response {
    private boolean ok;
}
