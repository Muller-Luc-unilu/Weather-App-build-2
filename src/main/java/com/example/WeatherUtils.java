package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WeatherUtils {

    public record WeatherEntry(String name, int count) {}

    public String weatherScore(List<String> descriptions) 
    {
        Map<String, Integer> counts = new HashMap<>();

        for (String description : descriptions) 
        {
        counts.merge(description, 1, Integer::sum);
        }

        return counts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Unknown");
    }

    public List<WeatherForecast> filtertoDaytime(List<WeatherForecast> forecasts) {
        return forecasts.stream().filter(f -> f.time().contains("09:00:00") || f.time().contains("12:00:00")|| f.time().contains("15:00:00")|| f.time().contains("18:00:00")|| f.time().contains("21:00:00")).toList();
    }

    public int setAverageTemperature(List<Double> temperature) {
        return (int)(temperature.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
    }
    public int setAverage_Humidity(List<Integer> humidity)
    {
       return (int) Math.round(humidity.stream().mapToInt(i -> i).average().orElse(0));
    }

    public Map<String, List<WeatherForecast>> getMap(List<WeatherForecast>forecast, Map<String, List<WeatherForecast>> byDay)
    {
        for(WeatherForecast f : forecast) 
        {
            String date = f.time().substring(0, 10);
            byDay.computeIfAbsent(date, d -> new ArrayList<>()).add(f);
        }
        return byDay;
    }
}
