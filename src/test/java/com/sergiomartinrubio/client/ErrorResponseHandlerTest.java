package com.sergiomartinrubio.client;

import com.sergiomartinrubio.exception.AuthenticationException;
import com.sergiomartinrubio.exception.InvalidRequestException;
import com.sergiomartinrubio.exception.ResourceNotFoundException;
import com.sergiomartinrubio.exception.TelegramException;
import com.sergiomartinrubio.model.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ErrorResponseHandlerTest {

    private static final String RESOURCE = "/path";
    private static final String DESCRIPTION = "reason";

    private static final ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler();

    @Test
    void shouldThrowInvalidRequestExceptionWhen400() {
        // GIVEN
        ErrorResponse errorResponse = new ErrorResponse("400", DESCRIPTION);

        // WHEN
        assertThatThrownBy(() -> errorResponseHandler.handle(errorResponse, RESOURCE))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test
    void shouldThrowAuthenticationExceptionWhen401() {
        // GIVEN
        ErrorResponse errorResponse = new ErrorResponse("401", DESCRIPTION);

        // WHEN
        assertThatThrownBy(() -> errorResponseHandler.handle(errorResponse, RESOURCE))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhen404() {
        // GIVEN
        ErrorResponse errorResponse = new ErrorResponse("404", DESCRIPTION);

        // WHEN
        assertThatThrownBy(() -> errorResponseHandler.handle(errorResponse, RESOURCE))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldTelegramExceptionWhenUnknownCode() {
        // GIVEN
        ErrorResponse errorResponse = new ErrorResponse("500", DESCRIPTION);

        // WHEN
        assertThatThrownBy(() -> errorResponseHandler.handle(errorResponse, RESOURCE))
                .isInstanceOf(TelegramException.class);
    }

}
