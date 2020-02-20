package org.example;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public interface BotService {
    void run();

    void sendMessage(WeatherMessageReplyer weatherMessageReplyer, Message messageTelegram) throws IOException, TelegramApiException;
}
