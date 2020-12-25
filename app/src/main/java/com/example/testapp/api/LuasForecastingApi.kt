package com.example.testapp.api

import com.example.testapp.domain.Forecast
import io.reactivex.Single
import retrofit2.http.GET

interface LuasForecastingApi {

    @GET("/xml/get.ashx?action=forecast&stop=mar&encrypt=false")
    fun getMarlboroughOutbound(): Single<Forecast>

    @GET("/xml/get.ashx?action=forecast&stop=sti&encrypt=false")
    fun getStillorganInbound(): Single<Forecast>
}