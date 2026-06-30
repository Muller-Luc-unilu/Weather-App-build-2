package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try {

            String city;

            if (args.length > 0) {
                city = args[0];
                System.out.println("Using city from command line: " + city);
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter city: ");
                city = scanner.nextLine();
                scanner.close();
            }

            List<Double> temperature = new ArrayList<>();
            WeatherUtils weatherUtils = new WeatherUtils();
            List<String> descriptions = new ArrayList<>();
            List<Integer> humidities = new ArrayList<>();
            Map<String, List<WeatherForecast>> byDay = new HashMap<>();

            WeatherApi api = new WeatherApi();
            WeatherParser parser = new WeatherParser();

            String json = api.getForecast(city);
            List<WeatherForecast> forecasts = parser.parse(json);
            List<WeatherForecast> forecasts_filtered_daytime = weatherUtils.filtertoDaytime(forecasts);
            byDay = weatherUtils.getMap(forecasts_filtered_daytime, byDay);

            for (WeatherForecast f : forecasts_filtered_daytime) {
                temperature.add(f.temperature());
                descriptions.add(f.description());
                humidities.add(f.humidity());
            }
            String score = weatherUtils.weatherScore(descriptions);
            int avg_temperature = weatherUtils.setAverageTemperature(temperature);
            int avg_humidity = weatherUtils.setAverage_Humidity(humidities);

            System.out.println("================================");
            System.out.println("WEATHER FORECAST FOR " + city.toUpperCase());
            System.out.println("================================");
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("WEATHER FOR THE UPCOMING HOURS");
            System.out.println("--------------------------------");
            for (int i = 0; i < 3; i++) {
                String time = forecasts.get(i).time().substring(11, 16); // HH:mm
                double temp = forecasts.get(i).temperature();
                String desc = forecasts.get(i).description();
                int humidity = forecasts.get(i).humidity();
                System.out
                        .println(time + "   " + (int) temp + "°C  " + desc + " with an humidity of: " + humidity + "%");
            }

            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("        DAILY SUMMARY");
            System.out.println("--------------------------------");
            System.out.println("Date       | temp | Hum.| Avg Sky");
            for (var entry : byDay.entrySet()) {
                String date = entry.getKey();
                List<WeatherForecast> dayForecasts = entry.getValue();

                List<Double> temps = dayForecasts.stream().map(WeatherForecast::temperature).toList();
                List<Integer> hums = dayForecasts.stream().map(WeatherForecast::humidity).toList();
                List<String> descs = dayForecasts.stream().map(WeatherForecast::description).toList();

                int avgTemp = weatherUtils.setAverageTemperature(temps);
                int avgHum = weatherUtils.setAverage_Humidity(hums);
                String sky = weatherUtils.weatherScore(descs);
                System.out.println(date + " | " + avgTemp + "°C | " + avgHum + "% | " + sky);
            }
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("TOTAL AVERAGES FOR UPCOMING DAYS  ");
            System.out.println("--------------------------------");
            System.out.println("Temp: " + avg_temperature + "°C");
            System.out.println("Day Time Sky: " + score);
            System.out.println("Humidity: " + avg_humidity + "%");
            System.out.println();
            System.out.println();
            System.out.println("================================");
            System.out.println("=            ENDING            =");
            System.out.println("================================");
        } catch (Exception e) {
            System.out.println("Unable to retrieve weather data.");
            System.out.println("Please check the city name or your internet connection.");
        }
    }
}

