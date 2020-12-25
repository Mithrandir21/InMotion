package com.example.testapp.repo

import com.example.testapp.api.LuasForecastingApi
import com.example.testapp.db.ForecastDao
import com.example.testapp.domain.Direction
import com.example.testapp.domain.Forecast
import com.example.testapp.domain.TramDirection
import com.example.testapp.utils.Optional
import com.example.testapp.utils.time.TimeBeforeNoon
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class ForecastRepoImpl @Inject constructor(
    private val forecastingApi: LuasForecastingApi,
    private val forecastDao: ForecastDao,
    private val timeTool: TimeBeforeNoon
) : ForecastRepo {

    /** Observe the most recent [Forecast] available. Wrapped in an [Optional] to allow empty values through, indicating no available [Forecast]. */
    override fun observeForecast(): Observable<Optional<Forecast>> =
        forecastDao.observeForecast().toObservable().map { Optional.of(it.firstOrNull()) }

    /** Attempt to reload the most up to date [Forecast] based on time of day, before or after Noon. */
    override fun reloadForecast(): Completable =
        Single.just(timeTool.isBeforeNoon())
            .flatMap { isBeforeNoon ->
                when (isBeforeNoon) {
                    true -> forecastingApi.getMarlboroughOutbound().map { it to TramDirection.OUTBOUND }
                    false -> forecastingApi.getStillorganInbound().map { it to TramDirection.INBOUND }
                }
            }
            .map { (forecast, tramDirection) -> forecast.copy(direction = forecast.directionalTimes(tramDirection)) }
            .doOnSuccess { forecastDao.clearAndReplace(it) }
            .ignoreElement()

    private fun Forecast.directionalTimes(tramDirection: TramDirection): List<Direction> = direction.filter { it.direction == tramDirection }
}