package org.weatherBotDevelopers;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.objects.Update;

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
                botService.onUpdateReceived(new Update());
            }).start();
        }
    }
}
