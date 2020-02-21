package org.weatherBotDevelopers;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.Scanner;

public class Weather {

    //api call link: http://api.openweathermap.org/data/2.5/weather?q=London&units=metric&APPID=561769eddcb1c6a87b5738e2c1d8f783

    public static String getWeather(String text) throws IOException {
        String result = "";

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + text + "&units=metric&APPID=561769eddcb1c6a87b5738e2c1d8f783");

        Scanner scanner = new Scanner((InputStream) url.getContent());

        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        Gson gson = new Gson();
        String weatherPattern = "";
        ModelJSON modelJSON = gson.fromJson(result, ModelJSON.class);

        for (ModelJSON.WeatherPattern weatherPattern1 : modelJSON.weather) {
            weatherPattern = weatherPattern1.toString();
        }

        return "City: " + modelJSON.name + "\n" +
                "Temperature: " + modelJSON.main.temp + " C" + "\n" +
                "Main: " + weatherPattern + "\n" +
                "Feels like: " + modelJSON.main.feels_like + "\n" +
                "Humidity: " + modelJSON.main.humidity + " %" + "\n" +
                "Wind speed: " + modelJSON.wind.speed + " м/с" + "\n";

    }
}
