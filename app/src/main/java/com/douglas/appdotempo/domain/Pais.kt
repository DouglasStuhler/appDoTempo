package com.douglas.appdotempo.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pais (
    val iso2: String,
    val iso3: String,
    val country: String,
    val cities: List<String> //Cidades do pais
)