package org.example;

import com.petersamokhin.bots.sdk.clients.Group;

import java.io.FileInputStream;
import java.util.Properties;

public class VkWeatherBot implements BotService{

    Group group;

    @Override
    public void auth() {
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
    public void sendMessage(MessageReplyer messageReplyer) {
        messageReplyer.sendVkMessage(group);
    }
}
