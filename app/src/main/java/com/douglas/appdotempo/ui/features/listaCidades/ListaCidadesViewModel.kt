package com.douglas.appdotempo.ui.features.listaCidades

import com.douglas.appdotempo.ui.features.listaPaises.ListaPaisesEvents
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas.appdotempo.data.solicitacoesCidades
import com.douglas.appdotempo.domain.Pais
import com.douglas.appdotempo.ui.UIEvent
import com.douglas.to_dolist.navigation.DetalhesCidadeRoute
import com.douglas.to_dolist.navigation.ListCidadeRoute
import com.douglas.to_dolist.navigation.ListPaisesRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ListaCidadesViewModel (
    nomePais: String,
    private val repository: solicitacoesCidades
): ViewModel() {

    var listaCidade by mutableStateOf(emptyList<String>())
        private set

    var nomeCidade by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch{
            val lista= repository.getCidades(nomePais)
            listaCidade = lista
        }
    }

    fun onEvent(event: ListaCidadesEvents){
        when(event){
            is ListaCidadesEvents.onClick -> {
                nomeCidade = event.nomeCidade
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.Navigate(DetalhesCidadeRoute(event.nomeCidade)))
                }
            }
        }
    }
}