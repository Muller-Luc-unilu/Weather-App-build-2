package com.example;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        WeatherApi api = new WeatherApi();
        WeatherParser parser = new WeatherParser();

        String json = api.getForecast(city);
        List<WeatherForecast> forecasts = parser.parse(json);

        for (WeatherForecast f : forecasts) {
            System.out.println("At " + f.time() + " it will be " +
                    f.temperature() + "°C and " + f.description());
        }

        scanner.close();
    }
}



