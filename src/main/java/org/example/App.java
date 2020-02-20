package org.example;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        BotService[] botServices = {
                new VkWeatherBot(),
                new TelegramWeatherBot()
        };

        for (BotService botService : botServices) {
            new Thread(() -> {
                botService.run();
                try {
                    botService.sendMessage(new WeatherMessageReplyer(), new Message());
                } catch (IOException | TelegramApiException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
