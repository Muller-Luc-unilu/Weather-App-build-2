package com.example;
import java.util.List;

import com.google.gson.Gson;

public class WeatherParser {

    private final Gson gson = new Gson();

    public List<WeatherForecast> parse(String json) {
        APIResponse response = gson.fromJson(json, APIResponse.class);

        return response.list().stream().map(e -> new WeatherForecast(e.time(),e.main().temp(),e.weather().get(0).description())).toList();
    }
}
