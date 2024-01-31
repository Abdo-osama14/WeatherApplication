package com.example.weatherapplication.home.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Pair<Double,Double>?
}