package org.example;

public class ModelJSON {
    public String name;
    public Double temperature;
    public Double humidity;
    //private String icon;
    public String main;
    public Double windSpeed;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /*public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }*/

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
