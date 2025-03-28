package com.douglas.appdotempo.ui.features.listaCidades

interface ListaCidadesEvents {
    data class onClick(val nomeCidade: String): ListaCidadesEvents
}