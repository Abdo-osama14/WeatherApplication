package com.example.weatherapplication.home.presentation

import com.example.weatherapplication.home.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)