package com.sergiomartinrubio.client;

import com.sergiomartinrubio.http.ClientHttpRequest;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.ResponseMessage;
import com.sergiomartinrubio.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
    private ClientHttpRequest clientHttpRequest;

    @Test
    void shouldSendMessage() {
        // GIVEN
        String path = "/sendMessage";
        HttpMethod method = HttpMethod.POST;
        User from = new User(USER_ID, true, "java", "cool_java_bot");
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var firstResponseMessage = new ResponseMessage(FIRST_MESSAGE_ID, from, chat, 1626859986L, FIRST_MESSAGE);
        var secondResponseMessage = new ResponseMessage(SECOND_MESSAGE_ID, from, chat, 1626859987L, SECOND_MESSAGE);
        var firstMessage = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        var secondMessage = new BotMessage(CHAT_ID, SECOND_MESSAGE);
        when(clientHttpRequest.doExecute(path, method, firstMessage)).thenReturn(firstResponseMessage);
        when(clientHttpRequest.doExecute(path, method, secondMessage)).thenReturn(secondResponseMessage);

        // WHEN
        ResponseMessage firstResponse = telegramBotClientImpl.sendMessage(CHAT_ID, FIRST_MESSAGE);
        ResponseMessage secondResponse = telegramBotClientImpl.sendMessage(CHAT_ID, SECOND_MESSAGE);

        // THEN
        assertThat(firstResponse).isEqualTo(firstResponseMessage);
        assertThat(secondResponse).isEqualTo(secondResponseMessage);
    }

}
