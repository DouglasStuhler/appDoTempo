package com.douglas.appdotempo.ui.features.listaCidades

interface ListaCidadesEvents {
    data class onClick(val nomeCidade: String): ListaCidadesEvents
    data class onEvent(val pais : String, val pesquisa: String): ListaCidadesEvents
}