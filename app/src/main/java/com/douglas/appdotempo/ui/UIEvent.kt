package com.douglas.appdotempo.ui

sealed interface UIEvent {

    data object NavigateBack : UIEvent
    data class Navigate<T : Any>(val route: T) : UIEvent
}