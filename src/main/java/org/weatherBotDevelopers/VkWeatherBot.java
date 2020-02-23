package org.weatherBotDevelopers;

import com.petersamokhin.bots.sdk.clients.Group;

import java.io.FileInputStream;
import java.util.Properties;

public class VkWeatherBot implements BotService {

    Group group;

    @Override
    public void initialize(WeatherMessageReplyer weatherMessageReplyer) {
        weatherMessageReplyer.response = "VkWeatherBot is started";
    }

    @Override
    public void launch() {
        System.out.println("authVk");
        group = new Group(188205376, getProperties());
        group.onSimpleTextMessage(message -> {
            new com.petersamokhin.bots.sdk.objects.Message().from(group).to(message.authorId()).text(new WeatherMessageReplyer().sendReply(message.getText())).send();
        });
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
}
