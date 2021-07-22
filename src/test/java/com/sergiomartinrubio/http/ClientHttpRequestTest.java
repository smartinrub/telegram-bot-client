package com.sergiomartinrubio.http;

import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.http.model.HttpMethod;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.Result;
import com.sergiomartinrubio.model.User;
import com.sergiomartinrubio.utils.CustomHttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientHttpRequestTest {
    private static final long USER_ID = 1881024015L;
    private static final long CHAT_ID = -489903905L;
    private static final int FIRST_MESSAGE_ID = 9;
    private static final String FIRST_MESSAGE = "first message";
    private static final String BASE_URL = "https://api.telegram.org/bot";

    @Mock
    private HttpClient httpClient;

    private ClientHttpRequest clientHttpRequest;

    @BeforeEach
    void setup() {
        clientHttpRequest = new ClientHttpRequest(httpClient, BASE_URL);
    }

    @Test
    void shouldExecuteRequest() throws IOException, InterruptedException {
        // GIVEN
        User from = new User(USER_ID, true, "java", "cool_java_bot");
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var messageResult = new Result(FIRST_MESSAGE_ID, from, chat, 1626859986L, FIRST_MESSAGE);
        var responseMessage = new Message(messageResult);
        BotMessage botMessage = new BotMessage(CHAT_ID, FIRST_MESSAGE);
        HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL + "/sendMessage"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"chat_id\":-489903905,\"text\":\"first message\"}"))
                .build();
        when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(new CustomHttpResponse());

        // WHEN
        Response result = clientHttpRequest.execute("/sendMessage", HttpMethod.POST, botMessage);

        // THEN
        assertThat(result).isEqualTo(responseMessage);
    }

}
