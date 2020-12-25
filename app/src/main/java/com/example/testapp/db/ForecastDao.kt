package com.example.testapp.db

import androidx.room.*
import com.example.testapp.domain.Forecast
import io.reactivex.Flowable

@Dao
interface ForecastDao {

    /** Returns a Flowable [Forecast]. Returned as List since it can be not found and Flowable does not allow null. */
    @Query("SELECT * FROM Forecast")
    fun observeForecast(): Flowable<List<Forecast>>

    /** Adds the [Forecast] to the database. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addForecast(forecast: Forecast)

    /** Deletes all [Forecast]s from the database. */
    @Query("DELETE FROM Forecast")
    fun clearForecast()

    @Transaction
    fun clearAndReplace(forecast: Forecast) {
        clearForecast()
        addForecast(forecast)
    }
}