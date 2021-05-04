package com.example.weatherappkotlin.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Main {
    @SerializedName("temp")
    @Expose
    var temp: Double = 0.toDouble()

    @SerializedName("humidity")
    @Expose
    var humidity: Double = 0.toDouble()

}