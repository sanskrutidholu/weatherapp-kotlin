package com.example.weatherappkotlin.models

import com.example.weatherappkotlin.WeatherActivity
import com.google.gson.annotations.SerializedName


class Example {

    @SerializedName("coord")
    internal  lateinit var coord: Coord

    @SerializedName("sys")
    internal  lateinit var sys: Sys

    @SerializedName("main")
    internal  lateinit var main: Main

    @SerializedName("wind")
    internal  lateinit var wind: Wind

    @SerializedName("dt")
     internal var dt: Long = 0

}