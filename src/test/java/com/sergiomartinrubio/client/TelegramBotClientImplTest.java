package com.sergiomartinrubio.client;

import com.sergiomartinrubio.exception.TelegramException;
import com.sergiomartinrubio.http.HttpRequestClientImpl;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.ForwardMessage;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.ErrorResponse;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.SuccessfulResponse;
import com.sergiomartinrubio.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sergiomartinrubio.client.utils.Methods.FORWARD_MESSAGE_PATH;
import static com.sergiomartinrubio.client.utils.Methods.GET_ME_PATH;
import static com.sergiomartinrubio.client.utils.Methods.SEND_MESSAGE_PATH;
import static com.sergiomartinrubio.http.model.HttpMethod.GET;
import static com.sergiomartinrubio.http.model.HttpMethod.POST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelegramBotClientImplTest {
    private static final long USER_ID = 1881024015L;
    private static final long CHAT_ID = -489903905L;
    private static final long FROM_CHAT_ID = -589903905L;
    private static final int FIRST_MESSAGE_ID = 9;
    private static final int SECOND_MESSAGE_ID = 10;
    private static final String FIRST_MESSAGE = "first message";
    private static final String SECOND_MESSAGE = "second message";
    private static final String FIRST_NAME = "java";
    private static final String USERNAME = "cool_java_bot";

    @InjectMocks
    private TelegramBotClientImpl telegramBotClientImpl;

    @Mock
    private HttpRequestClientImpl httpRequestClient;

    @Test
    void shouldSendMessage() {
        // GIVEN
        User from = new User(USER_ID, true, FIRST_NAME, USERNAME, null, null, null);
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var firstMessageResult = new Message(FIRST_MESSAGE_ID, from, 1626859986L, chat, null, null, FIRST_MESSAGE);
        var secondMessageResult = new Message(SECOND_MESSAGE_ID, from, 1626859987L, chat, null, null, SECOND_MESSAGE);
        var firstResponseMessage = new SuccessfulResponse(firstMessageResult);
        var secondResponseMessage = new SuccessfulResponse(secondMessageResult);
        var firstMessage = BotMessage.builder()
                .chatId(CHAT_ID)
                .text(FIRST_MESSAGE)
                .build();
        var secondMessage = BotMessage.builder()
                .chatId(CHAT_ID)
                .text(SECOND_MESSAGE)
                .build();
        when(httpRequestClient.execute(SEND_MESSAGE_PATH, POST, firstMessage)).thenReturn(firstResponseMessage);
        when(httpRequestClient.execute(SEND_MESSAGE_PATH, POST, secondMessage)).thenReturn(secondResponseMessage);

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
        var message = BotMessage.builder()
                .chatId(CHAT_ID)
                .text(FIRST_MESSAGE)
                .build();
        when(httpRequestClient.execute(SEND_MESSAGE_PATH, POST, message)).thenReturn(responseMessage);

        // WHEN
        assertThatThrownBy(() -> telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE))
                .isInstanceOf(TelegramException.class);
    }

    @Test
    void shouldReturnUser() {
        // GIVEN
        User user = new User(USER_ID, true, FIRST_NAME, USERNAME, true, true, false);
        SuccessfulResponse response = new SuccessfulResponse(user);
        when(httpRequestClient.execute(GET_ME_PATH, GET)).thenReturn(response);

        // WHEN
        User result = telegramBotClientImpl.getMe();

        // THEN
        assertThat(result).isEqualTo(user);
    }

    @Test
    void shouldForwardMessage() {
        // GIVEN
        User from = new User(USER_ID, true, FIRST_NAME, USERNAME, null, null, null);
        var chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var message = new Message(FIRST_MESSAGE_ID, from, 1626859986L, chat, null, null, FIRST_MESSAGE);
        var response = new SuccessfulResponse(message);
        ForwardMessage forwardMessage = ForwardMessage.builder()
                .chatId(CHAT_ID)
                .fromChatId(FROM_CHAT_ID)
                .messageId(FIRST_MESSAGE_ID)
                .build();
        when(httpRequestClient.execute(FORWARD_MESSAGE_PATH, GET, forwardMessage)).thenReturn(response);

        // WHEN
        Message result = telegramBotClientImpl.forwardMessage(CHAT_ID, FROM_CHAT_ID, FIRST_MESSAGE_ID);

        // THEN
        assertThat(result).isEqualTo(message);
    }

}
