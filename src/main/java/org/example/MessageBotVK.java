package org.example;

import com.vk.api.sdk.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class MessageBotVK {

    String text = "";

    public void getMessage(App app) throws IOException {

        String jsonAsString = "";

        try {
            jsonAsString = app.a.executeAsString();
            GetLongPollServerResponse resp = app.a.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(jsonAsString);
        JSONObject response = jsonObject.getJSONObject("response");

        String key = response.getString("key");
        String server = response.getString("server");
        Integer ts = response.getInt("ts");

        // https://{$server}?act=a_check&key={$key}&ts={$ts}&wait=25&mode=2&version=2

        URL url = new URL(server + "?act=a_check&key=" + key + "&ts=" + ts + "&wait=25&mode=2&version=2");

        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        System.out.println(result);

        JSONObject object = new JSONObject(result);
        JSONArray updates = object.getJSONArray("updates");
        JSONObject insideUpdates = new JSONObject();

        for (int i = 0; i < updates.length(); i++) {
            JSONObject objectObject = updates.getJSONObject(i);
            insideUpdates = objectObject.getJSONObject("object");
        }

        JSONObject messageObject = insideUpdates.getJSONObject("message");
        text = messageObject.getString("text");

        //System.out.println(text);
    }
}
