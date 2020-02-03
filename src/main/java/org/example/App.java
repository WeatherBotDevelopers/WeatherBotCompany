package org.example;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new App());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        BotService[] botServices = {
                new VkWeatherBot(),
        };

        for (BotService botService : botServices) {
            new Thread(() -> {
                while (true) {
                    botService.sendMessage();
                }
            }).start();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            //setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        ModelJSON model = new ModelJSON();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            try {
                sendMsg(message, Weather.getWeather(model, message.getText()));
            } catch (IOException e) {
                sendMsg(message, "City is not found!");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "SimpleQuickBot";
    }

    @Override
    public String getBotToken() {
        Properties properties = new Properties();
        String token = null;
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            token = String.valueOf(properties.getProperty("tokenTelegram"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
