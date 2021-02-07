package com.example.android_projekt

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://opentable.herokuapp.com/"
private const val BASE_URL = "https://ratpark-api.imok.space/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("api/restaurants?name=Chicago")
    fun getProperties():
            Call<String>
}

object RestaurantsApi{
    val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}