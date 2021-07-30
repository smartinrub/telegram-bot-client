package com.sergiomartinrubio.http.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiomartinrubio.http.model.ForwardMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ForwardMessageSerializerTest {

    @Test
    void shouldSerializeBotMessage() throws IOException {
        // GIVEN
        var mapper = new ObjectMapper();
        var botMessage = ForwardMessage.builder()
                .chatId(1234)
                .fromChatId(4321)
                .messageId(6789)
                .build();

        // WHEN
        String result = mapper.writeValueAsString(botMessage);

        // THEN
        assertThat(result).isEqualTo("{\"chat_id\":1234,\"from_chat_id\":4321,\"message_id\":6789}");
    }

}
