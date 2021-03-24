package com.example.harbapp.networking

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val OWM_URL = "https://api.openweathermap.org/"

class OwmRetrofit @Inject constructor() {

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OWM_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}