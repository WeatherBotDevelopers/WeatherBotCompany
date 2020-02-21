package org.weatherBotDevelopers;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public interface BotService {
    void run();

    void sendMessage(WeatherMessageReplyer weatherMessageReplyer, Message messageTelegram);

    void onUpdateReceived(Update update);
}
