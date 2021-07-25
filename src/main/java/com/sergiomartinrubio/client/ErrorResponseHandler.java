package com.sergiomartinrubio.client;

import com.sergiomartinrubio.exception.AuthenticationException;
import com.sergiomartinrubio.exception.InvalidRequestException;
import com.sergiomartinrubio.exception.ResourceNotFoundException;
import com.sergiomartinrubio.exception.TelegramException;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Response;

public class ErrorResponseHandler {

    public void handle(Response response, String path) {
        ErrorResponse errorResponse = (ErrorResponse) response;
        var errorCode = errorResponse.getErrorCode();
        var description = errorResponse.getDescription();
        switch (errorCode) {
            case "400":
                throw new InvalidRequestException(description);
            case "401":
                throw new AuthenticationException(description);
            case "404":
                throw new ResourceNotFoundException(path, description);
            default:
                throw new TelegramException(errorCode, description);
        }
    }
}
