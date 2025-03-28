package com.douglas.appdotempo.data


import kotlinx.serialization.Serializable

@Serializable
data class Loc(
    var longitude: Double,
    var latitude: Double

)

