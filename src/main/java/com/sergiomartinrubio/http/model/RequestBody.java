package com.sergiomartinrubio.http.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Marker interface for any request body sent to the Telegram API.
 *
 * @author Sergio Martin Rubio
 */
public interface RequestBody {

    /**
     * Converts request body implementation to a JSON string.
     *
     * @param mapper the object mapper that contains serializers
     * @return a string with a JSON format
     */
    default String toJson(ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
