package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ErrorResponse extends Response {

    @JsonProperty("error_code")
    private String errorCode;

    private String description;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(errorCode, that.errorCode) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errorCode, description);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
