package org.example;

<<<<<<< HEAD
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//changed comment for commit
=======
import org.telegram.telegrambots.ApiContextInitializer;
>>>>>>> unitingBots

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
