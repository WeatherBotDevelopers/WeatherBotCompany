package org.weatherBotDevelopers;

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
    public void initialize(WeatherMessageReplyer weatherMessageReplyer) {
        weatherMessageReplyer.response = "TelegramWeatherBot is started";
    }

    @Override
    public void launch() {
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

    public void sendMessage(WeatherMessageReplyer weatherMessageReplyer, Message messageTelegram) {
        SendMessage sendMessage = new SendMessage();
        if (messageTelegram.hasText()) {
            try {
                sendMessage.enableMarkdown(true);
                sendMessage.setChatId(messageTelegram.getChatId().toString());
                sendMessage.setReplyToMessageId(messageTelegram.getMessageId());
                sendMessage.setText(weatherMessageReplyer.sendReply(messageTelegram.getText()));
                sendMessage(sendMessage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        messageTelegram = update.getMessage();
        if (messageTelegram != null) {
            sendMessage(new WeatherMessageReplyer(), messageTelegram);
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
