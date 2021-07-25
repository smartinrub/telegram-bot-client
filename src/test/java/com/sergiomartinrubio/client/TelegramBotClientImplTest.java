package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.Result;
import com.sergiomartinrubio.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private TelegramBotClientImpl telegramBotClientImpl;

    @Mock
    private ErrorResponseHandler errorResponseHandler;

    @Mock
    private ClientHttpRequest clientHttpRequest;

    @Test
    void shouldSendMessage() {
        // GIVEN
        String path = "/sendMessage";
        HttpMethod method = HttpMethod.POST;
        User from = new User(USER_ID, true, "java", "cool_java_bot");
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var firstMessageResult = new Result(FIRST_MESSAGE_ID, from, chat, 1626859986L, FIRST_MESSAGE);
        var secondMessageResult = new Result(SECOND_MESSAGE_ID, from, chat, 1626859987L, SECOND_MESSAGE);
        var firstResponseMessage = new Message(firstMessageResult);
        var secondResponseMessage = new Message(secondMessageResult);
        var firstMessage = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        var secondMessage = new BotMessage(CHAT_ID, SECOND_MESSAGE);
        when(clientHttpRequest.execute(path, method, firstMessage)).thenReturn(firstResponseMessage);
        when(clientHttpRequest.execute(path, method, secondMessage)).thenReturn(secondResponseMessage);

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
        String path = "/sendMessage";
        HttpMethod method = HttpMethod.POST;
        var firstResponseMessage = new ErrorResponse();
        var firstMessage = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        when(clientHttpRequest.execute(path, method, firstMessage)).thenReturn(firstResponseMessage);

        // WHEN
        Response firstResponse = telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE);

        // THEN
        verify(errorResponseHandler).handle(firstResponse, path);
    }

}
