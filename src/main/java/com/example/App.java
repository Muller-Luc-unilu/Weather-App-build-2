package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws Exception {

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
    String score = weatherUtils.weatherScore(descriptions);

    WeatherApi api = new WeatherApi();
    WeatherParser parser = new WeatherParser();

    String json = api.getForecast(city);
    List<WeatherForecast> forecasts = parser.parse(json);

    List<WeatherForecast> forecasts_filtered_daytime = weatherUtils.filtertoDaytime(forecasts);

    for (WeatherForecast f : forecasts_filtered_daytime) {
        temperature.add(f.temperature());
        descriptions.add(f.description());
    }

    int avg_temperature = weatherUtils.setAverageTemperature(temperature);

    System.out.println("================================");
    System.out.println("WEATHER FORECAST FOR " + city.toUpperCase());
    System.out.println("================================");
    System.out.println();
    System.out.println("Average Day Temp: " + avg_temperature + "°C");
    System.out.println("Average Day Time Sky: " + score);
    System.out.println();
    System.out.println("================================");
    System.out.println("=            ENDING            =");
    System.out.println("================================");
}
}
