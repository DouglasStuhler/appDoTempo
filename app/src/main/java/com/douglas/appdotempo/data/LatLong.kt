package com.douglas.appdotempo.data


import kotlinx.serialization.Serializable

@Serializable
data class LatLong(
    var longitude: Double,
    var latitude: Double

)

