package org.weatherBotDevelopers;

public class WeatherMessageReplyer {

    String response = "";

    public String sendReply(String text) {
        try {
            response = Weather.getWeather(text);
        } catch (Exception ex) {
            response = "Такого города не существует!";
        }
        return response;
    }
}