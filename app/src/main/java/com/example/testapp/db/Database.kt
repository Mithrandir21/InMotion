package com.example.testapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.domain.DirectionsConverters
import com.example.testapp.domain.Forecast
import com.example.testapp.domain.TramConverters

@Database(version = 1, entities = [Forecast::class], exportSchema = false)
@TypeConverters(DirectionsConverters::class, TramConverters::class)
abstract class Database : RoomDatabase() {

    abstract fun getForecastDao(): ForecastDao
}