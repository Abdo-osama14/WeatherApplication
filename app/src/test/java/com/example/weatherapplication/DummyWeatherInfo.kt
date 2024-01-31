package com.example.weatherapplication

import com.example.weatherapplication.home.domain.weather.WeatherData
import com.example.weatherapplication.home.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DummyWeatherInfo {
     val DummyWeatherList = arrayListOf(
        WeatherData(getLocalDateTime("2023-12-18 15:30:00"),22.5,10.4,11.1,11.1,WeatherType.ClearSky),
        WeatherData(getLocalDateTime("2023-12-18 15:30:00"),22.5,10.4,11.1,11.1,WeatherType.ClearSky),
        WeatherData(getLocalDateTime("2023-12-18 15:30:00"),22.5,10.4,11.1,11.1,WeatherType.ClearSky),
        WeatherData(getLocalDateTime("2023-12-18 15:30:00"),22.5,10.4,11.1,11.1,WeatherType.ClearSky),
        WeatherData(getLocalDateTime("2023-12-18 15:30:00"),22.5,10.4,11.1,11.1,WeatherType.ClearSky)
    )

    val getDummyWeatherMap = convertListToMap(DummyWeatherList)

    private fun convertListToMap(inputList: List<WeatherData>): Map<Int, List<WeatherData>> {
        val resultMap = mutableMapOf<Int, List<WeatherData>>()

        for ((index, value) in inputList.withIndex()) {
            resultMap[index] = listOf(value)
        }

        return resultMap
    }


    fun getLocalDateTime(inputString: String): LocalDateTime? {
        return try {
            // Assuming the input string is in a valid date and time format
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val inputDateTime = LocalDateTime.parse(inputString, formatter)
            LocalDateTime.now()
        } catch (e: Exception) {
            null // Handle the case of an invalid date and time format
        }
    }
}

