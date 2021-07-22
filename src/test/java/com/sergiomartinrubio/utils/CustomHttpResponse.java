package com.sergiomartinrubio.utils;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

/*
{
   "ok":true,
   "result":{
      "message_id":16,
      "from":{
         "id":1881024015,
         "is_bot":true,
         "first_name":"java",
         "username":"cool_java_bot"
      },
      "chat":{
         "id":-489903905,
         "title":"Java",
         "type":"group",
         "all_members_are_administrators":true
      },
      "date":1626951549,
      "text":"hello!"
   }
}
 */
public class CustomHttpResponse implements HttpResponse<String> {

    @Override
    public int statusCode() {
        return 0;
    }

    @Override
    public HttpRequest request() {
        return null;
    }

    @Override
    public Optional<HttpResponse<String>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @Override
    public String body() {
        return "{\n" +
                "   \"ok\":true,\n" +
                "   \"result\":{\n" +
                "      \"message_id\":9,\n" +
                "      \"from\":{\n" +
                "         \"id\":1881024015,\n" +
                "         \"is_bot\":true,\n" +
                "         \"first_name\":\"java\",\n" +
                "         \"username\":\"cool_java_bot\"\n" +
                "      },\n" +
                "      \"chat\":{\n" +
                "         \"id\":-489903905,\n" +
                "         \"title\":\"Java\",\n" +
                "         \"type\":\"group\",\n" +
                "         \"all_members_are_administrators\":true\n" +
                "      },\n" +
                "      \"date\":1626859986,\n" +
                "      \"text\":\"first message\"\n" +
                "   }\n" +
                "}";
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public HttpClient.Version version() {
        return null;
    }
}
