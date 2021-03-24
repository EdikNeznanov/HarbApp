package com.example.harbapp.networking

import com.example.harbapp.model.HarbResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HarbCalls {
    @GET("harbors/visible")
    fun getHarbours(): Single<List<HarbResponse>>
}