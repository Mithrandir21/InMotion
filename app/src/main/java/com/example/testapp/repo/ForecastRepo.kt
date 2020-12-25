package com.example.testapp.repo

import com.example.testapp.domain.Forecast
import com.example.testapp.utils.Optional
import io.reactivex.Completable
import io.reactivex.Observable

interface ForecastRepo {

    /** Observe the most recent [Forecast] available. Wrapped in an [Optional] to allow empty values through, indicating no available [Forecast]. */
    fun observeForecast(): Observable<Optional<Forecast>>

    /** Attempt to reload the most up to date [Forecast] based on time of day, before or after Noon. */
    fun reloadForecast(): Completable
}