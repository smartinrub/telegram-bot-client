package com.sergiomartinrubio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiomartinrubio.http.model.BotMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class BotMessageSerializerTest {

    @Test
    void shouldSerializeBotMessage() throws IOException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        // WHEN
        String result = mapper.writeValueAsString(new BotMessage(1234, "hello"));

        // THEN
        assertThat(result).isEqualTo("{\"chat_id\":1234,\"text\":\"hello\"}");
    }

}
