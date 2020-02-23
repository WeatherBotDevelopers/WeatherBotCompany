package org.weatherBotDevelopers;

import org.telegram.telegrambots.ApiContextInitializer;

public class App {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramWeatherBot telegramWeatherBot = new TelegramWeatherBot();
        telegramWeatherBot.initialize(new WeatherMessageReplyer());
        telegramWeatherBot.launch();

        VkWeatherBot vkWeatherBot = new VkWeatherBot();
        vkWeatherBot.initialize(new WeatherMessageReplyer());
        vkWeatherBot.launch();
    }
}
