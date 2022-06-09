package com.example.apps10xtask.ui.di

import com.example.apps10xtask.ui.data.api.WeatherApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by bhupendrapatel on 09/06/22.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl(): String = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApiPostService(retrofit: Retrofit): WeatherApiServices =
        retrofit.create(WeatherApiServices::class.java)
}