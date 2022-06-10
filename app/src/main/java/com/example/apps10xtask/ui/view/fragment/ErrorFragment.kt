package com.example.apps10xtask.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.apps10xtask.R
import com.example.apps10xtask.databinding.FragmentErrorBinding
import com.example.apps10xtask.databinding.FragmentWeatherInfoBinding

class ErrorFragment : Fragment() {

    lateinit var fragmentErrorBinding: FragmentErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentErrorBinding = FragmentErrorBinding.inflate(inflater, container, false)

        fragmentErrorBinding.retry.setOnClickListener {
            Navigation.findNavController(fragmentErrorBinding.root)
                .navigate(R.id.action_errorFragment_to_weatherInfoFragment)
        }

        return fragmentErrorBinding.root
    }

}