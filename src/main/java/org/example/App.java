package org.example;

import org.telegram.telegrambots.ApiContextInitializer;

public class App {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        BotService[] botServices = {
                new VkWeatherBot(),
                new TelegramWeatherBot()
        };

        for (BotService botService : botServices) {
            new Thread(() -> {
                botService.auth();
                botService.sendMessage(new MessageReplyer());
            }).start();
        }
    }
}
