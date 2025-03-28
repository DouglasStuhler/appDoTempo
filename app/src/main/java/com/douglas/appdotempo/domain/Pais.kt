package com.douglas.appdotempo.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pais (
    val iso2: String,
    val iso3: String,
    val country: String,
    val cities: List<String> //Cidades do pais
)

val paises = listOf(
    Pais (
        iso2 = "asdasdas",
        iso3 = "asdasdas",
        country = "Teste 01",
        cities = listOf("Teste 01", "Teste 02", "Teste 03")
    ),
    Pais (
        iso2 = "asdasdas",
        iso3 = "asdasdas",
        country = "Teste 02",
        cities = listOf("Teste 01", "Teste 02", "Teste 03")
    )
)