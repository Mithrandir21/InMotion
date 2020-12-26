package com.example.testapp.api

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitProvider @Inject constructor() {

    private var retrofit: Retrofit? = null

    /** Returns an instance of `Retrofit`. */
    fun getRetrofit(): Retrofit =
        retrofit ?: createRetrofit()


    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://luasforecasts.rpa.ie/")
            .client(createHttpClient())
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    private fun createHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
}