package com.example.weatherapplication.home.domain.repository

import com.example.weatherapplication.home.domain.utill.Resource
import com.example.weatherapplication.home.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}