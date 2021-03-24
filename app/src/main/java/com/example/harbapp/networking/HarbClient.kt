package com.example.harbapp.networking

import com.example.harbapp.model.HarbResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class HarbClient @Inject constructor(harbRetrofit: HarbRetrofit) {
    private var retrofit: Retrofit = harbRetrofit.create()

    fun retrieveHarbours(): Single<List<HarbResponse>> {
        val harbCalls = retrofit.create(HarbCalls::class.java)
        return harbCalls.getHarbours()
    }
}