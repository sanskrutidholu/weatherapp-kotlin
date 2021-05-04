package com.example.weatherappkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappkotlin.models.Example
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    lateinit var cityname : EditText
    lateinit var search : Button
    lateinit var temp : TextView
    lateinit var humidityview : TextView
    lateinit var longitude : TextView
    lateinit var latitude : TextView
    lateinit var sunrises : TextView
    lateinit var sunsets : TextView
    lateinit var countryname :TextView
    lateinit var windspeed : TextView
    lateinit var windDegree : TextView
    lateinit var date : TextView

    var url : String = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}"
    var apikey : String = "9510150edda1fbd77152bf112c568865"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)

        cityname = findViewById(R.id.et_cityname)
        temp = findViewById(R.id.et_temp)
        humidityview = findViewById(R.id.et_humi)
        longitude = findViewById(R.id.et_long)
        latitude = findViewById(R.id.et_lat)
        countryname = findViewById(R.id.et_countryname)
        sunrises = findViewById(R.id.et_sunrise)
        sunsets = findViewById(R.id.et_sunset)
        windspeed = findViewById(R.id.et_speed)
        windDegree = findViewById(R.id.et_degree)
        date = findViewById(R.id.et_date)
        search = findViewById(R.id.btn_search)

        search.setOnClickListener {

            val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val myapi : weatherapi = retrofit.create(weatherapi::class.java)
            val examplecall : Call<Example> = myapi.getweather(cityname.text.toString().trim(),apikey)
            examplecall.enqueue(object : retrofit2.Callback<Example>{
                override fun onResponse(call: Call<Example>, response: Response<Example>) {

                    val mydata : Example = response.body()!!

                    // calling main object from example class and its members
                    val main = mydata.main
                    val temps : Double = main.temp
                    val temperature : Int = (temps - 273).toInt()
                    temp.text = temperature.toString() + "°C"
                    val humidity = main.humidity
                    humidityview.text = "$humidity%"

                    // calling coord object from example class and its members
                    val coord = mydata.coord
                    val long : Double = coord.lon
                    longitude.text = "Long: $long"
                    val lat : Double = coord.lat
                    latitude.text = "Lat: $lat"

                    // calling sys object from example class and its members
                    val sys = mydata.sys
                    val sunr : Long = sys.sunrise!!
                    val sunrise : String = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunr * 1000))
                    sunrises.text = sunrise
                    val suns : Long = sys.sunset!!
                    val sunset : String = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(suns * 1000))
                    sunsets.text = sunset
                    val count : String = sys.country
                    countryname.text = count

                    // calling wind object from example class and its members
                    val wind = mydata.wind
                    val speed : Double = wind.speed!!
                    windspeed.text = speed.toString() + " km/hr"
                    val degree :Int = wind.deg!!
                    windDegree.text = degree.toString() + "°"

                    //  calling object date from example
                    val dt : Long = mydata.dt
                    val upadatedat = "Updated at " + SimpleDateFormat("dd/MM/yyyy , hh:mm a", Locale.ENGLISH).format(Date(dt * 1000))
                    date.text = upadatedat

                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    Log.d("error","sometning went wrong",t)

                }
            })
        }
    }
}