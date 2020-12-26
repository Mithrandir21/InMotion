package com.example.testapp.domain

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tickaroo.tikxml.TypeConverter

class TramDirectionConverters: TypeConverter<TramDirection> {
    override fun read(value: String?): TramDirection = Gson().fromJson(value, object : TypeToken<TramDirection>() {}.type)

    override fun write(value: TramDirection): String = Gson().toJson(value)
}


class DirectionsConverters {

    @androidx.room.TypeConverter
    fun toString(obj: List<Direction>): String? = Gson().toJson(obj)

    @androidx.room.TypeConverter
    fun toList(string: String?): List<Direction> = Gson().fromJson(string, object : TypeToken<List<Direction>>() {}.type)
}

class TramConverters {

    @androidx.room.TypeConverter
    fun toString(obj: List<Tram>): String? = Gson().toJson(obj)

    @androidx.room.TypeConverter
    fun toList(string: String?): List<Tram> = Gson().fromJson(string, object : TypeToken<List<Tram>>() {}.type)
}