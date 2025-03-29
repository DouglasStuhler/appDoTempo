package com.douglas.appdotempo.ui.features.listaPaises

import ListCidadeRoute
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas.appdotempo.data.solicitacoesCidades
import com.douglas.appdotempo.domain.Pais
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ListaPaisesViewModel (
    private val repository: solicitacoesCidades
): ViewModel() {

    var listPais by mutableStateOf(emptyList<Pais>())
        private set

    var nomePais by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch{
            val listaPaises = repository.getPaises()
            listPais = listaPaises
        }
    }

    fun onEvent(event: ListaPaisesEvents){
        when(event){
            is ListaPaisesEvents.onClick -> {
                nomePais = event.nomePais
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.Navigate(ListCidadeRoute(event.nomePais)))
                }
            }
        }
    }
}