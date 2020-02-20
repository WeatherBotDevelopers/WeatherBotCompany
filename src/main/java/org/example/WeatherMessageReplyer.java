package org.example;

public class WeatherMessageReplyer extends TelegramWeatherBot {

    String response = "";

    public String sendReply(String text) {
        try {
            response = Weather.getWeather(new ModelJSON(), text);
        } catch (Exception ex) {
            response = "Такого города не существует!";
        }
        return response;
    }
}