package com.example.testapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.testapp.domain.Direction
import com.example.testapp.domain.Forecast
import com.example.testapp.domain.Tram
import com.example.testapp.domain.TramDirection
import com.example.testapp.repo.ForecastRepo
import com.example.testapp.testing.RxJavaSchedulerImmediateRule
import com.example.testapp.utils.Optional
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val liveDataImmediateRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxJavaSchedulerImmediateRule = RxJavaSchedulerImmediateRule()

    @Mock
    private lateinit var forecastRepo: ForecastRepo


    @Test
    fun initTestEmpty() {
        var eventValue: Pair<Forecast, List<Tram>>? = null

        val optional = Optional.of<Forecast>(null)

        whenever(forecastRepo.observeForecast()).thenReturn(Observable.just(optional))

        MainViewModel(forecastRepo)
            .apply { observeForecast().observe({ lifecycle() }) { eventValue = it } }


        Assert.assertEquals(null, eventValue)
    }

    @Test
    fun initTestFull() {
        var eventValue: Pair<Forecast, List<Tram>>? = null

        val tramA = Tram("5", "Destination")
        val list = listOf(tramA)
        val directionA = Direction(TramDirection.OUTBOUND, list)
        val forecast = Forecast("", listOf(directionA), "", "", "")
        val optional = Optional.of(forecast)

        val pair = forecast to list

        whenever(forecastRepo.observeForecast()).thenReturn(Observable.just(optional))

        MainViewModel(forecastRepo)
            .apply { observeForecast().observe({ lifecycle() }) { eventValue = it } }


        Assert.assertEquals(pair, eventValue)
    }

    @Test
    fun forceReloadForecastTest() {
        whenever(forecastRepo.observeForecast()).thenReturn(Observable.just(Optional.of(null)))
        whenever(forecastRepo.reloadForecast()).thenReturn(Completable.complete())

        MainViewModel(forecastRepo).forceReloadForecast()

        verify(forecastRepo, times(1)).observeForecast()
    }


    private fun lifecycle(): Lifecycle {
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        return lifecycle
    }
}