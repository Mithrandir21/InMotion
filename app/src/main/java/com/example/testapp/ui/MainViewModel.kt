package com.example.testapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.domain.Forecast
import com.example.testapp.domain.Tram
import com.example.testapp.repo.ForecastRepo
import com.example.testapp.utils.logging.debugLog
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val forecastRepo: ForecastRepo) : BaseViewModel() {

    private val data = MutableLiveData<Pair<Forecast, List<Tram>>>()

    init {
        // Rx stream that observe updated forecasts and transforms them to data for the view.
        forecastRepo.observeForecast()
            .map { it.value?.let { forecast -> forecast to forecast.direction.map { direction -> direction.tram }.flatten() } }
            .map { it.first to it.second.filter { tram -> tram.dueMins.isNotBlank() } }
            .debugLog()
            .subscribe(data::postValue) { error.postValue(it) }
            .let { compositeDisposable.addAll(it) }
    }

    /** Observe the most recent [Forecast] available and a list of [Tram] objects containing the actual due times and destinations. */
    fun observeForecast(): LiveData<Pair<Forecast, List<Tram>>> = data

    /** Force a reloading of the most recent [Forecast] from the [ForecastRepo], which will be based on time of day. */
    fun forceReloadForecast() = forecastRepo.reloadForecast()
        .mergeWith(Completable.timer(2, TimeUnit.SECONDS)) // Added timer for demonstration purposes.
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { startProgress() }
        .doOnError { stopProgress() }
        .doOnComplete { stopProgress() }
        .debugLog()
        .subscribe({ }) { error.postValue(it) }
        .let { compositeDisposable.addAll(it) }
}