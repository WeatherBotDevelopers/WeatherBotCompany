package org.weatherBotDevelopers;

public interface BotService {
    void initialize(WeatherMessageReplyer weatherMessageReplyer);

    void launch();
}
