package org.weatherBotDevelopers;

public class ModelJSON {

    String name;
    Main main;
    Wind wind;
    WeatherPattern[] weather;

    public class Main {
        String temp;
        String feels_like;
        String humidity;
    }

    public class Wind {
        String speed;
    }

    public class WeatherPattern {
        String main;

        @Override
        public String toString() {
            return main;
        }
    }
}
