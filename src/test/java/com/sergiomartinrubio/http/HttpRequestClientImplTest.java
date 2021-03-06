package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiomartinrubio.http.model.BotMessage;
import com.sergiomartinrubio.model.Chat;
import com.sergiomartinrubio.model.ChatType;
import com.sergiomartinrubio.model.Message;
import com.sergiomartinrubio.model.Response;
import com.sergiomartinrubio.model.SuccessfulResponse;
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

import static com.sergiomartinrubio.client.utils.Methods.GET_ME_PATH;
import static com.sergiomartinrubio.client.utils.Methods.SEND_MESSAGE_PATH;
import static com.sergiomartinrubio.http.model.HttpMethod.GET;
import static com.sergiomartinrubio.http.model.HttpMethod.POST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HttpRequestClientImplTest {
    private static final long USER_ID = 1881024015L;
    private static final long CHAT_ID = -489903905L;
    private static final int FIRST_MESSAGE_ID = 9;
    private static final String FIRST_MESSAGE = "first message";
    private static final String BASE_URL = "https://api.telegram.org/bot";
    private static final String FIRST_NAME = "java";
    private static final String USERNAME = "cool_java_bot";

    @Mock
    private HttpClient httpClient;

    private HttpRequestClientImpl httpRequestClient;

    @BeforeEach
    void setup() {
        httpRequestClient = new HttpRequestClientImpl(httpClient, BASE_URL, new ObjectMapper());
    }

    @Test
    void shouldExecuteRequest() throws IOException, InterruptedException {
        // GIVEN
        User from = new User(USER_ID, true, FIRST_NAME, USERNAME, null, null, null);
        Chat chat = new Chat(CHAT_ID, "Java", ChatType.GROUP, true);
        var message = new Message(FIRST_MESSAGE_ID, from, 1626859986L, chat, null, null, FIRST_MESSAGE);
        var response = new SuccessfulResponse(message);
        BotMessage botMessage = BotMessage.builder()
                .chatId(CHAT_ID)
                .text(FIRST_MESSAGE)
                .build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL + SEND_MESSAGE_PATH))
                .header("Content-Type", "application/json")
                .method(POST.name(), HttpRequest.BodyPublishers
                        .ofString("{\"chat_id\":-489903905,\"text\":\"first message\"}"))
                .build();
        String file = "message_response.json";
        when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(new CustomHttpResponse(file));

        // WHEN
        Response result = httpRequestClient.execute(SEND_MESSAGE_PATH, POST, botMessage);

        // THEN
        assertThat(result).isEqualTo(response);
    }

    @Test
    void shouldExecuteWithoutBody() throws IOException, InterruptedException {
        // GIVEN
        var user = new User(USER_ID, true, FIRST_NAME, USERNAME, true, false, false);
        var response = new SuccessfulResponse(user);
        HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL + GET_ME_PATH))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        String file = "user_response.json";
        when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(new CustomHttpResponse(file));

        // WHEN
        Response result = httpRequestClient.execute(GET_ME_PATH, GET);

        // THEN
        assertThat(result).isEqualTo(response);
    }

}
