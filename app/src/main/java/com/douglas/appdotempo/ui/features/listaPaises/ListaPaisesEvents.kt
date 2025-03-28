package com.douglas.appdotempo.ui.features.listaPaises

interface ListaPaisesEvents{
    data class onClick(val nomePais: String): ListaPaisesEvents
}