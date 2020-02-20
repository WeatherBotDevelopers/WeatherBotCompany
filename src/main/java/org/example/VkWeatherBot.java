package org.example;

import com.petersamokhin.bots.sdk.clients.Group;
import org.telegram.telegrambots.api.objects.Message;

import java.io.FileInputStream;
import java.util.Properties;

public class VkWeatherBot implements BotService {

    Group group;

    @Override
    public void run() {
        System.out.println("authVk");
        group = new Group(188205376, getProperties());
    }

    public String getProperties() {
        Properties properties = new Properties();
        String token = "";
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            token = String.valueOf(properties.getProperty("tokenVk"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void sendMessage(WeatherMessageReplyer weatherMessageReplyer, Message messageTelegram) {
        group.onSimpleTextMessage(message -> {
            new com.petersamokhin.bots.sdk.objects.Message().from(group).to(message.authorId()).text(weatherMessageReplyer.sendReply(message.getText())).send();
        });
    }
}
