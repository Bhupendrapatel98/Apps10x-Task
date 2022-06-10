package com.example.apps10xtask.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.apps10xtask.R
import com.example.apps10xtask.databinding.FragmentWeatherInfoBinding
import com.example.apps10xtask.ui.view.adapter.ForeCastAdapter
import com.example.apps10xtask.ui.viewModel.WeatherViewModel
import com.example.apps10xtask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

/**
 * Created by bhupendrapatel on 09/06/22.
 */
@AndroidEntryPoint
class WeatherInfoFragment : Fragment() {

    lateinit var fragmentWeatherInfoBinding: FragmentWeatherInfoBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    lateinit var animSlideDown: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentWeatherInfoBinding = FragmentWeatherInfoBinding.inflate(inflater, container, false)

        fragmentWeatherInfoBinding.lifecycleOwner = this

        weatherViewModel.getWeather()
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    fragmentWeatherInfoBinding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Failed -> {
                    fragmentWeatherInfoBinding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    fragmentWeatherInfoBinding.progressBar.visibility = View.GONE
                    fragmentWeatherInfoBinding.cardView.visibility = View.VISIBLE

                    val df = DecimalFormat("0")
                    val input = it.data.main.temp
                    fragmentWeatherInfoBinding.currentTemp.text = df.format(input) + "\u00B0"

                    fragmentWeatherInfoBinding.weather = it.data
                }
            }
        })

        weatherViewModel.getForecast()
        weatherViewModel.foreCastLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    fragmentWeatherInfoBinding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Failed -> {
                    fragmentWeatherInfoBinding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    fragmentWeatherInfoBinding.progressBar.visibility = View.GONE
                    fragmentWeatherInfoBinding.recyclerView.visibility = View.VISIBLE

                    val foreCastAdapter = ForeCastAdapter(it.data.list)
                    fragmentWeatherInfoBinding.recyclerView.adapter = foreCastAdapter

                    animSlideDown = AnimationUtils.loadAnimation(context, R.anim.slide_up)
                    fragmentWeatherInfoBinding.recyclerView.startAnimation(animSlideDown)
                }
            }
        })

        return fragmentWeatherInfoBinding.root
    }
}