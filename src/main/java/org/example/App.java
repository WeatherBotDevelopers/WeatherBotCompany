package org.example;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.io.IOException;

public class App {

    Group group = new Group(188205376, "29b9c2b8bc68974b87ebb9447649a016125edd478ff9d7de530af31e89f6232d8ba19cd8d6a64390f8ddd");
    String text = "";
    String messageToSend = "";

    public static void main(String[] args) throws IOException {

        App app = new App();

        while (true) {
            app.group.onSimpleTextMessage(message -> {
                try {
                    app.text = message.getText();
                    app.messageToSend = Weather.getWeather(new ModelJSON(), app);
                    new Message().from(app.group).to(message.authorId()).text(app.messageToSend).send();
                } catch (IOException e) {
                    new Message().from(app.group).to(message.authorId()).text("Такого города не существует!").send();
                }
            });
        }
    }
}
