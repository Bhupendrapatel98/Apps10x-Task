package com.example.apps10xtask.ui.data.api

import com.example.apps10xtask.ui.data.model.ForeCast
import com.example.apps10xtask.ui.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bhupendrapatel on 09/06/22.
 */
interface WeatherApiServices {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("APPID") APPID: String
    ): Weather

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") q: String,
        @Query("APPID") APPID: String
    ): ForeCast
}