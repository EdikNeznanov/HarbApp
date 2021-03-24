package com.example.harbapp.networking

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val HARB_URL = "https://devapi.harba.co/"
class HarbRetrofit @Inject constructor() {

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HARB_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}