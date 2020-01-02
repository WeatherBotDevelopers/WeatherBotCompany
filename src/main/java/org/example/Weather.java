package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //api call link: http://api.openweathermap.org/data/2.5/weather?q=London&units=metric&APPID=561769eddcb1c6a87b5738e2c1d8f783

    public static String getWeather(ModelJSON model, App app) throws IOException {

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + app.text + "&units=metric&APPID=561769eddcb1c6a87b5738e2c1d8f783");

        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemperature(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONObject wind = object.getJSONObject("wind");
        model.setWindSpeed(wind.getDouble("speed"));

        JSONArray getArray = object.getJSONArray("weather");

        for (int i = 0; i < getArray.length(); i++) {
            JSONObject object1 = getArray.getJSONObject(i);
            //model.setIcon(object1.getString("icon"));
            model.setMain(object1.getString("main"));
        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemperature() + " C" + "\n" +
                "Main: " + model.getMain() + "\n" +
                "Humidity: " + model.getHumidity() + " %" + "\n" +
                "Wind speed: " + model.getWindSpeed() + " м/с" + "\n";
        //"http://openweathermap.org/img/wn/" + model.getIcon() + "@2x.png";
    }
}
