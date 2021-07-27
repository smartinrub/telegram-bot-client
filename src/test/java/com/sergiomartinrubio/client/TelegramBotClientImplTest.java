package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequestImpl;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.Result;
import com.sergiomartinrubio.model.SuccessfulResponse;
import com.sergiomartinrubio.model.User;
import com.sergiomartinrubio.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sergiomartinrubio.client.utils.Methods.GET_ME;
import static com.sergiomartinrubio.client.utils.Methods.SEND_MESSAGE;
import static com.sergiomartinrubio.http.model.HttpMethod.GET;
import static com.sergiomartinrubio.http.model.HttpMethod.POST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelegramBotClientImplTest {
    private static final long USER_ID = 1881024015L;
    private static final long CHAT_ID = -489903905L;
    private static final int FIRST_MESSAGE_ID = 9;
    private static final int SECOND_MESSAGE_ID = 10;
    private static final String FIRST_MESSAGE = "first message";
    private static final String SECOND_MESSAGE = "second message";
    private static final String FIRST_NAME = "java";
    private static final String USERNAME = "cool_java_bot";

    @InjectMocks
    private TelegramBotClientImpl telegramBotClientImpl;

    @Mock
    private ErrorResponseHandler errorResponseHandler;

    @Mock
    private ClientHttpRequestImpl clientHttpRequestImpl;

    @Test
    void shouldSendMessage() {
        // GIVEN
        User from = new User(USER_ID, true, FIRST_NAME, USERNAME);
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var firstMessageResult = new Result(FIRST_MESSAGE_ID, from, chat, 1626859986L, FIRST_MESSAGE);
        var secondMessageResult = new Result(SECOND_MESSAGE_ID, from, chat, 1626859987L, SECOND_MESSAGE);
        var firstResponseMessage = new SuccessfulResponse(firstMessageResult);
        var secondResponseMessage = new SuccessfulResponse(secondMessageResult);
        var firstMessage = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        var secondMessage = new BotMessage(CHAT_ID, SECOND_MESSAGE);
        when(clientHttpRequestImpl.execute(SEND_MESSAGE, POST, firstMessage)).thenReturn(firstResponseMessage);
        when(clientHttpRequestImpl.execute(SEND_MESSAGE, POST, secondMessage)).thenReturn(secondResponseMessage);

        // WHEN
        Response firstResponse = telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE);
        Response secondResponse = telegramBotClientImpl.sendMessage(CHAT_ID, SECOND_MESSAGE);

        // THEN
        assertThat(firstResponse).isEqualTo(firstResponseMessage);
        assertThat(secondResponse).isEqualTo(secondResponseMessage);
    }

    @Test
    void shouldCallResponseErrorHandlerWhenErrorResponse() {
        // GIVEN
        var responseMessage = new ErrorResponse();
        var message = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        when(clientHttpRequestImpl.execute(SEND_MESSAGE, POST, message)).thenReturn(responseMessage);

        // WHEN
        Response response = telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE);

        // THEN
        verify(errorResponseHandler).handle(response, SEND_MESSAGE);
    }

    @Test
    void shouldReturnUser() {
        // GIVEN
        UserResponse user = new UserResponse(USER_ID, true, FIRST_NAME, USERNAME, true, true, false);
        SuccessfulResponse response = new SuccessfulResponse(user);
        when(clientHttpRequestImpl.execute(GET_ME, GET)).thenReturn(response);

        // WHEN
        Response result = telegramBotClientImpl.getMe();

        // THEN
        assertThat(result).isEqualTo(response);
    }

}
