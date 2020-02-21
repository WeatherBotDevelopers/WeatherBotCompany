package org.weatherBotDevelopers;

import org.telegram.telegrambots.ApiContextInitializer;

public class App {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramWeatherBot telegramWeatherBot = new TelegramWeatherBot();
        telegramWeatherBot.initialization(new WeatherMessageReplyer());

        VkWeatherBot vkWeatherBot = new VkWeatherBot();
        vkWeatherBot.initialization(new WeatherMessageReplyer());
    }
}
