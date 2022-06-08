package com.example.apps10xtask.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.apps10xtask.R
import com.example.apps10xtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}