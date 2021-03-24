package com.example.harbapp.networking

import com.example.harbapp.ApiKeys
import com.example.harbapp.model.OwmResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OwmCalls {

    @GET("/data/2.5/weather?appid=${ApiKeys.OPEN_WEATHER_KEY}")
    fun getWeather(@Query("lat") lat: Float, @Query("lon") lon: Float, ): Single<OwmResponse>

}