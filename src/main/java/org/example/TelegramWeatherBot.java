package org.example;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TelegramWeatherBot extends TelegramLongPollingBot implements BotService {

    @Override
    public void auth() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            System.out.println("authTeleg");
            telegramBotsApi.registerBot(new TelegramWeatherBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getProperties() {
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

    @Override
    public void sendMessage(MessageReplyer messageReplyer) {
        //because of the stupid this method is empty
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            MessageReplyer messageReplyer = new MessageReplyer();
            messageReplyer.sendTelegramMessage(update);
        }
    }

    @Override
    public String getBotUsername() {
        return "SimpleQuickBot";
    }

    @Override
    public String getBotToken() {
        return getProperties();
    }
}
