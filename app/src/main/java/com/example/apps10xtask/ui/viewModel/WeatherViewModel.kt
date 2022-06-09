package com.example.apps10xtask.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apps10xtask.data.model.Weather
import com.example.apps10xtask.data.repo.WeatherRepo
import com.example.apps10xtask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by bhupendrapatel on 09/06/22.
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _weatherLiveData = MutableLiveData<Resource<Weather>>()
    val weatherLiveData: LiveData<Resource<Weather>>
        get() = _weatherLiveData

    fun getWeather() {
        viewModelScope.launch {
            _weatherLiveData.value = Resource.loading()

            weatherRepo.getWeather("Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40")
                .catch { e ->
                    Log.d("dfjdfh111", "getWeather: "+e.message.toString())
                    _weatherLiveData.value = Resource.failed(e.message.toString())
                }.collect() { data ->
                    Log.d("dfjdfh2222", "getWeather: "+data)
                    _weatherLiveData.value = Resource.success(data)
                }
        }
    }

}