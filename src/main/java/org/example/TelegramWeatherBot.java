package org.example;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TelegramWeatherBot extends TelegramLongPollingBot implements BotService {

    Message messageTelegram;

    @Override
    public void run() {
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
    public void sendMessage(WeatherMessageReplyer weatherMessageReplyer, Message messageTelegram) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        if (messageTelegram.hasText()) {
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(messageTelegram.getChatId().toString());
            sendMessage.setReplyToMessageId(messageTelegram.getMessageId());
            sendMessage.setText(weatherMessageReplyer.sendReply(messageTelegram.getText()));
            sendMessage(sendMessage);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        messageTelegram = update.getMessage();
        try {
            sendMessage(new WeatherMessageReplyer(), messageTelegram);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
