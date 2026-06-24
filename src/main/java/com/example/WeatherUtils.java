package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class WeatherUtils {

    public record WeatherEntry(String name, int count,String output) {}

    List<WeatherEntry> WEATHER = new ArrayList<>(List.of(
            new WeatherEntry("clear sky", 0,"clear sky"),
            new WeatherEntry("few clouds", 0,"few clouds"),
            new WeatherEntry("scattered clouds", 0,"few scattered clouds"),
            new WeatherEntry("broken clouds", 0 ,"few broken clouds"),
            new WeatherEntry("shower rain", 0," bit of shower rain"),
            new WeatherEntry("rain", 0," bit of rain"),
            new WeatherEntry("thunderstorm", 0,"thunderstorm"),
            new WeatherEntry("snow", 0,"a bit ofsnow"),
            new WeatherEntry("mist", 0,"a bit misty")
    ));

    public String weatherScore(List<String> descriptions) {

        // Count occurrences
        for (String desc : descriptions) {
            for (int i = 0; i < WEATHER.size(); i++) {
                WeatherEntry e = WEATHER.get(i);
                if (e.name().equals(desc)) {
                    WEATHER.set(i, new WeatherEntry(e.name(), e.count() + 1, e.output()));
                }
            }
        }
        // Find max
        return WEATHER.stream()
                .max(Comparator.comparingInt(WeatherEntry::count))
                .map(WeatherEntry::output)
                .orElse("");
    }
    public List<WeatherForecast> filtertoDaytime(List<WeatherForecast> forecasts) {
        return forecasts.stream()
                .filter(f -> f.time().contains("09:00:00") || f.time().contains("12:00:00")|| f.time().contains("15:00:00")|| f.time().contains("18:00:00")|| f.time().contains("21:00:00"))
                .toList();
    }

    public int setAverageTemperature(List<Double> temperature) {
        return (int)(temperature.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
    }
}
