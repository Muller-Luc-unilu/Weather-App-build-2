package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApi{

    private final String apikey = "7c51019338db6a416fa09f226ced1ada";
    private final HttpClient client = HttpClient.newHttpClient();

    public String getForecast(String city) throws Exception {
        var url = "https://api.openweathermap.org/data/2.5/forecast?q="
                + city + "&appid=" + apikey + "&units=metric";

        var request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
