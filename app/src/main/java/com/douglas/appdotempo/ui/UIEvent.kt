package com.douglas.appdotempo.ui

sealed interface UIEvent {

    data class ShowError(val message: String) : UIEvent

    data object NavigateBack : UIEvent
    data class Navigate<T : Any>(val route: T) : UIEvent

    data class EnviaDadosFiltroCidade(val cidades: List<String>) : UIEvent
}