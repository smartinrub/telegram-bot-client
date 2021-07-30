package com.sergiomartinrubio.http.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiomartinrubio.http.model.BotMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class BotSuccessfulResponseSerializerTest {

    @Test
    void shouldSerializeBotMessage() throws IOException {
        // GIVEN
        var mapper = new ObjectMapper();
        var botMessage = BotMessage.builder()
                .chatId(1234)
                .text("hello")
                .build();

        // WHEN
        String result = mapper.writeValueAsString(botMessage);

        // THEN
        assertThat(result).isEqualTo("{\"chat_id\":1234,\"text\":\"hello\"}");
    }

}
