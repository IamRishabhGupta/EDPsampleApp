package com.example.edp

import com.google.gson.annotations.SerializedName

//import com.google.gson.annotations.SerializedName

data class MyData(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("time") val time: String,
    @SerializedName("weather_condition") val weatherCondition: String,
    @SerializedName("nearby_places") val nearbyPlaces: String,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("pwm") val pwm: String,
    @SerializedName("__v") val v: Int,
    @SerializedName("location") val location: String
)
