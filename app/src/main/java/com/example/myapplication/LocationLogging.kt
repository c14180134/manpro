package com.example.myapplication


@IgnoreExtraProperties

data class LocationLogging(
    var Latitude: Double? = 0.0,
    var Longitude: Double? = 0.0
)

annotation class IgnoreExtraProperties
