package com.example.apps10xtask.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.apps10xtask.R
import com.example.apps10xtask.databinding.FragmentWeatherInfoBinding
import com.example.apps10xtask.ui.viewModel.WeatherViewModel
import com.example.apps10xtask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by bhupendrapatel on 09/06/22.
 */
@AndroidEntryPoint
class WeatherInfoFragment : Fragment() {

    lateinit var fragmentWeatherInfoBinding: FragmentWeatherInfoBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentWeatherInfoBinding = FragmentWeatherInfoBinding.inflate(inflater, container, false)

        fragmentWeatherInfoBinding.lifecycleOwner = this

        weatherViewModel.getWeather()
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                     Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failed -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        return fragmentWeatherInfoBinding.root
    }
}