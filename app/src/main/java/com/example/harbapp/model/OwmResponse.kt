package com.example.harbapp.model

import com.google.gson.annotations.SerializedName

data class OwmResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

data class Coord(
    val lon: Float,
    val lat: Float
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
    val pressure: Float,
    val humidity: Int
)

data class Wind(val speed: Float, val deg: Float)

data class Clouds(val all: Int)

data class Sys(
    val type: Int,
    val id: Long,
    val message: String,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
