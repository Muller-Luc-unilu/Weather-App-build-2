package com.example;

import java.util.List;

public record APIResponse(List<ForecastEntry> list) {
}
