package com.example.testapp.repo

import com.example.testapp.api.LuasForecastingApi
import com.example.testapp.db.ForecastDao
import com.example.testapp.domain.Direction
import com.example.testapp.domain.Forecast
import com.example.testapp.utils.Optional
import com.example.testapp.utils.time.TimeBeforeNoon
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForecastRepoImplTest {

    @Mock
    private lateinit var forecastingApi: LuasForecastingApi

    @Mock
    private lateinit var forecastDao: ForecastDao

    @Mock
    private lateinit var timeTool: TimeBeforeNoon


    @Test
    fun itemsShouldBeObserved() {
        val forecast = mock<Forecast>()
        val listOfForecast = listOf(forecast)
        val flow = Flowable.just(listOfForecast)

        whenever(forecastDao.observeForecast()).thenReturn(flow)

        ForecastRepoImpl(forecastingApi, forecastDao, timeTool)
            .observeForecast()
            .test()
            .assertNoErrors()
            .assertValue { it.value == forecast }

        verify(forecastDao, times(1)).observeForecast()
    }

    @Test
    fun newItemsShouldBeLoadedBeforeNoon() {
        val directionList = listOf<Direction>()
        val forecast = Forecast("", directionList, "", "", "")
        val single = Single.just(forecast)

        whenever(timeTool.isBeforeNoon()).thenReturn(true)
        whenever(forecastingApi.getMarlboroughOutbound()).thenReturn(single)

        ForecastRepoImpl(forecastingApi, forecastDao, timeTool)
            .reloadForecast()
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(forecastDao, times(1)).clearAndReplace(any())
        verify(timeTool, times(1)).isBeforeNoon()
        verify(forecastingApi, times(1)).getMarlboroughOutbound()
        verify(forecastingApi, times(0)).getStillorganInbound()
    }

    @Test
    fun newItemsShouldBeLoadedAfterNoon() {
        val directionList = listOf<Direction>()
        val forecast = Forecast("", directionList, "", "", "")
        val single = Single.just(forecast)

        whenever(timeTool.isBeforeNoon()).thenReturn(false)
        whenever(forecastingApi.getStillorganInbound()).thenReturn(single)

        ForecastRepoImpl(forecastingApi, forecastDao, timeTool)
            .reloadForecast()
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(forecastDao, times(1)).clearAndReplace(any())
        verify(timeTool, times(1)).isBeforeNoon()
        verify(forecastingApi, times(0)).getMarlboroughOutbound()
        verify(forecastingApi, times(1)).getStillorganInbound()
    }
}