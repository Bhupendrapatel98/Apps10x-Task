package com.example.apps10xtask.data.repo

import com.example.apps10xtask.data.api.WeatherApiServices
import com.example.apps10xtask.data.model.ForeCast
import com.example.apps10xtask.data.model.Weather
import com.example.apps10xtask.data.model.WeatherReport
import com.example.apps10xtask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by bhupendrapatel on 09/06/22.
 */
class WeatherRepo @Inject constructor(private val weatherApiServices: WeatherApiServices) {

    fun getWeather(q: String, appId: String, units: String): Flow<Resource<WeatherReport>> = flow {
        emit(Resource.loading())

        val data = coroutineScope {
            val temp = async { weatherApiServices.getWeather(q, appId, units) }
            val forecast = async { weatherApiServices.getForecast(q, appId, units) }
            WeatherReport(temp.await(), forecast.await())
        }
        emit(Resource.success(data))

    }.catch {
        emit(Resource.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}