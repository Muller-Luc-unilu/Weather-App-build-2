package com.example;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record ForecastEntry(
        @SerializedName("dt_txt") String time,
        Main main,
        List<Weather> weather
) {}
