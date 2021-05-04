package com.example.weatherappkotlin

import com.example.weatherappkotlin.models.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherapi {
    @GET("weather")
    fun getweather(@Query("q") cityname:String,
                   @Query("appid") apikey:String): Call<Example>
}