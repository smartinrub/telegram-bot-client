# telegram-bot-client

## Installation

Copy and paste
the [telegram-bot-client](https://search.maven.org/artifact/com.sergiomartinrubio/telegram-bot-client/0.0.3/jar)
Maven dependency to your `pom.xml` file.

```xml

<dependency>
    <groupId>com.sergiomartinrubio</groupId>
    <artifactId>telegram-bot-client</artifactId>
    <version>0.0.3</version>
</dependency>
```

## Usage/Examples

### Create Telegram Bot Client

```java
TelegramBotClient client=TelegramBotClientFactory
        .createClient("<BOT_TOKEN>");
```

The `<BOT_TOKEN>` looks like `123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11`
and can be created via the [BotFather](https://t.me/botfather) as described [here](https://core.telegram.org/bots#).

### Get Me

Returns basic information about the bot.

```java
User response=telegramBotClient.getMe();
```

### Send Message

Sends a message to a given Telegram chat.

```java
Message response=telegramBotClient.sendMessage(<CHAT_ID>,"Hello World!");
```

`<CHAT_ID>` looks like `-489903905L`.

You can get the Chat ID by executing the following request:

```shell
curl -X GET https://api.telegram.org/bot<BOT_TOKEN>/getUpdates
```

> If you get `{"ok":true,"result":[]}`, remove and add the bot again to the group.

### Forward Message

Forwards a message from a particular chat to a given Telegram chat.

```java
Message response=telegramBotClient.forwardMessage(<SOURCE_CHAT_ID>,<DESTINATION_CHAT_ID>,<MESSAGE_ID>);
```

`<SOURCE_CHAT_ID>` and `<DESTINATION_CHAT_ID>` looks like `-489903905L`.

## Running Tests

To run tests, run the following command

```bash
  mvn clean test
```

## Contributing

Anyone is encouraged to contribute to the repository
by [forking](https://docs.github.com/en/get-started/quickstart/fork-a-repo) and submitting a pull request. (If you are
new to GitHub, you might start with a [basic tutorial](https://docs.github.com/en/get-started/quickstart/set-up-git). By
contributing to this project, you grant a world-wide, royalty-free, perpetual, irrevocable, non-exclusive, transferable
license to all users under the terms of
the [Apache Software License v2](http://www.apache.org/licenses/LICENSE-2.0.html) or later.
