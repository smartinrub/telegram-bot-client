package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents an HTTP error response from the Telegram API.
 *
 * @author Sergio Martin Rubio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends Response {

    /**
     * The HTTP error code. i.e. "400", "401", "500".
     */
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * A short description of the error.
     */
    private String description;
}
