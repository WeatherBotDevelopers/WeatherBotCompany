package org.example;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.queries.groups.GroupsGetLongPollServerQuery;

import java.io.IOException;

//changed comment for commit

public class App {

    TransportClient transportClient = HttpTransportClient.getInstance();
    VkApiClient vk = new VkApiClient(transportClient);
    GroupActor actor = new GroupActor(188205376, "29b9c2b8bc68974b87ebb9447649a016125edd478ff9d7de530af31e89f6232d8ba19cd8d6a64390f8ddd");
    GroupsGetLongPollServerQuery a = vk.groups().getLongPollServer(actor);

    public static void main(String[] args) throws ClientException, ApiException, IOException {
        while (true) {
            App app = new App();
            MessageBotVK messageBotVK = new MessageBotVK();
            messageBotVK.getMessage(new App());
            app.vk.messages().send(app.actor, 312138559).message("You have been said: " + messageBotVK.text).execute();
            try {
                String messageToSend = Weather.getWeather(new ModelJSON(), messageBotVK);
                app.vk.messages().send(app.actor, 312138559).message(messageToSend).execute();
            } catch (Exception e) {
                app.vk.messages().send(app.actor, 312138559).message("City is not found!").execute();
            }
        }
    }
}
