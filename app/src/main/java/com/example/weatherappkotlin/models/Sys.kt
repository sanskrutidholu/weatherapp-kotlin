package com.example.weatherappkotlin.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Sys {

    @SerializedName("country")
    @Expose
    lateinit var country: String

    @SerializedName("sunrise")
    @Expose
    var sunrise: Long? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Long? = null
}