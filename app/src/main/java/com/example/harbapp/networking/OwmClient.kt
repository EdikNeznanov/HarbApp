package com.example.harbapp.networking

import com.example.harbapp.model.OwmResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class OwmClient @Inject constructor(owmRetrofit: OwmRetrofit) {

    private var retrofit: Retrofit = owmRetrofit.create()

    fun retrieveWeather(lat: Float, lon: Float): Single<OwmResponse> {
        val womCalls = retrofit.create(OwmCalls::class.java)
        return womCalls.getWeather(lat, lon)
    }
}