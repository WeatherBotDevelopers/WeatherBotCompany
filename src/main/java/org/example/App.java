package org.example;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//changed comment for commit

public class App {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        String token = "";
        int groupId = 0;

        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            token = String.valueOf(properties.getProperty("token"));
            groupId = Integer.parseInt(properties.getProperty("groupId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Group group = new Group(groupId, token);

        while (true) {
            group.onSimpleTextMessage(message -> {
                try {
                    String text = message.getText();
                    String messageToSend = Weather.getWeather(new ModelJSON(), text);
                    new Message().from(group).to(message.authorId()).text(messageToSend).send();
                } catch (IOException e) {
                    new Message().from(group).to(message.authorId()).text("Такого города не существует!").send();
                }
            });
        }
    }
}
