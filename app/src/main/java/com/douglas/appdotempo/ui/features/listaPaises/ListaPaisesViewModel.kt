package com.douglas.appdotempo.ui.features.listaPaises

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class ListaPaisesViewModel (
    private val repository: solci
): ViewModel() {

    var nomePais by mutableStateOf("")
        private set

    var idPais by mutableStateOf(null)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch(

        )
    }
}