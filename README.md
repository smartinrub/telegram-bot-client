# telegram-bot-client


## Installation

Copy and paste the following Maven dependency to your `pom.xml` file.

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
TelegramBotClient client = TelegramBotClientFactory
        .createClient("<BOT_TOKEN>");
```

The `<BOT_TOKEN>` looks like `123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11`
and can be created via the [BotFather](https://t.me/botfather) as 
described [here](https://core.telegram.org/bots#).

### Get Me

Returns basic information about the bot.

```java
User response = telegramBotClient.getMe();
```

### Send Message

Sends a message to a given Telegram chat.

```java
Message response = telegramBotClient.sendMessage(<CHAT_ID>, "Hello World!");
```

`<CHAT_ID>` looks like `-489903905L`.

You can get the Chat ID by executing the following request:

```shell
curl -X GET https://api.telegram.org/bot<BOT_TOKEN>/getUpdates
```

> If you get `{"ok":true,"result":[]}`, remove and add the bot again to the group.

## Running Tests

To run tests, run the following command

```bash
  mvn clean test
```
