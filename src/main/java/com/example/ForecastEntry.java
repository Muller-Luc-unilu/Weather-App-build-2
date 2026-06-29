package com.example;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public record ForecastEntry(
                @SerializedName("dt_txt") String time,
                Main main,
                List<Weather> weather) {
}
