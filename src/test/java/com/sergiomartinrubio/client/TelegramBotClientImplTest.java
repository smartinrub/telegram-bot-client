package com.sergiomartinrubio.client;

import com.sergiomartinrubio.exception.TelegramException;
import com.sergiomartinrubio.http.ClientHttpRequestImpl;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.From;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.SuccessfulResponse;
import com.sergiomartinrubio.model.User;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    private ClientHttpRequestImpl clientHttpRequestImpl;

    @Test
    void shouldSendMessage() {
        // GIVEN
        From from = new From(USER_ID, true, FIRST_NAME, USERNAME);
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var firstMessageResult = new Message(FIRST_MESSAGE_ID, from, chat, 1626859986L, FIRST_MESSAGE);
        var secondMessageResult = new Message(SECOND_MESSAGE_ID, from, chat, 1626859987L, SECOND_MESSAGE);
        var firstResponseMessage = new SuccessfulResponse(firstMessageResult);
        var secondResponseMessage = new SuccessfulResponse(secondMessageResult);
        var firstMessage = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        var secondMessage = new BotMessage(CHAT_ID, SECOND_MESSAGE);
        when(clientHttpRequestImpl.execute(SEND_MESSAGE, POST, firstMessage)).thenReturn(firstResponseMessage);
        when(clientHttpRequestImpl.execute(SEND_MESSAGE, POST, secondMessage)).thenReturn(secondResponseMessage);

        // WHEN
        Message firstResult = telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE);
        Message secondResult = telegramBotClientImpl.sendMessage(CHAT_ID, SECOND_MESSAGE);

        // THEN
        assertThat(firstResult).isEqualTo(firstMessageResult);
        assertThat(secondResult).isEqualTo(secondMessageResult);
    }

    @Test
    void shouldCallResponseErrorHandlerWhenErrorResponse() {
        // GIVEN
        var responseMessage = new ErrorResponse();
        var message = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        when(clientHttpRequestImpl.execute(SEND_MESSAGE, POST, message)).thenReturn(responseMessage);

        // WHEN
        assertThatThrownBy(() -> telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE))
                .isInstanceOf(TelegramException.class);
    }

    @Test
    void shouldReturnUser() {
        // GIVEN
        User user = new User(USER_ID, true, FIRST_NAME, USERNAME, true, true, false);
        SuccessfulResponse response = new SuccessfulResponse(user);
        when(clientHttpRequestImpl.execute(GET_ME, GET)).thenReturn(response);

        // WHEN
        User result = telegramBotClientImpl.getMe();

        // THEN
        assertThat(result).isEqualTo(user);
    }

}
