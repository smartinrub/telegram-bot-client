package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the base class for any response returned by the Telegram API.
 *
 * @author Sergio Martin Rubio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "ok")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SuccessfulResponse.class, name = "true"),
        @JsonSubTypes.Type(value = ErrorResponse.class, name = "false")
})
public class Response {
    /**
     * If 'ok' equals true, the request was successful, {@link SuccessfulResponse} is use as
     * base class and the result of the query can be found in the {@link Result} field. In
     * case of an unsuccessful request, 'ok' equals false, {@link ErrorResponse} is use as
     * base class and the error is explained in the 'description'.
     */
    private boolean ok;
}
