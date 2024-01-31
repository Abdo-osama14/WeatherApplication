package com.example.weatherapplication

import android.location.Location
import com.example.weatherapplication.DummyWeatherInfo.getDummyWeatherMap
import com.example.weatherapplication.home.data.location.DefaultLocationTracker
import com.example.weatherapplication.home.domain.repository.WeatherRepository
import com.example.weatherapplication.home.domain.utill.Resource
import com.example.weatherapplication.home.domain.weather.WeatherData
import com.example.weatherapplication.home.domain.weather.WeatherInfo
import com.example.weatherapplication.home.domain.weather.WeatherType
import com.example.weatherapplication.home.presentation.WeatherViewModel
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
    private lateinit var viewModel: WeatherViewModel

    @Mock
    private lateinit var repository: WeatherRepository


    @Mock
    private lateinit var tracker: DefaultLocationTracker

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun release() {
        Dispatchers.resetMain()
    }

    @Test
    fun ` When State Success then get Weather Data `() = runTest {
        //arrange
        val expectedLat = 0.0
        val expectedLang = 0.0

        val expectedResult = Resource.Success(
            WeatherInfo(
                getDummyWeatherMap,
                WeatherData(
                    DummyWeatherInfo.getLocalDateTime("2023-12-18 15:30:00"),
                    22.5,
                    10.4,
                    11.1,
                    11.1,
                    WeatherType.ClearSky
                ),
            )
        )

        Mockito.`when`(tracker.getCurrentLocation()).thenReturn(
            Pair(expectedLat, expectedLang)
        )

        Mockito.`when`(
            repository.getWeatherData(
                expectedLat,
                expectedLang
            )
        ).thenReturn(
            expectedResult
        )

        //act

        viewModel = getViewModel()
        viewModel.loadWeatherInfo()

        //assert
        assertTrue(viewModel.state.weatherInfo != null)

    }

    private fun getViewModel(): WeatherViewModel {
        return WeatherViewModel(repository, tracker)
    }
}