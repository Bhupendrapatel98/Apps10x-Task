package com.example.apps10xtask.data.repo

import com.example.apps10xtask.data.api.WeatherApiServices
import com.example.apps10xtask.data.model.ForeCast
import com.example.apps10xtask.data.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by bhupendrapatel on 09/06/22.
 */
class WeatherRepo @Inject constructor(private val weatherApiServices: WeatherApiServices) {

    fun getWeather(q: String, APPID: String,units:String): Flow<Weather> = flow {
        val response = weatherApiServices.getWeather(q, APPID,units)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getForecast(q: String, APPID: String,units:String): Flow<ForeCast> = flow {
        val response = weatherApiServices.getForecast(q, APPID,units)
        emit(response)
    }.flowOn(Dispatchers.IO)
}