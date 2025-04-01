package com.douglas.appdotempo.ui.features.prevDetalhada

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas.appdotempo.data.pegaTempo
import com.douglas.appdotempo.data.solicitacoesCidades
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class prevDetalhadaViewModel(
    private val repositoryCidade: solicitacoesCidades,
    private val repositoryPrevisao: pegaTempo,
    private val cidade: String
): ViewModel() {

    var previsao : Previsao? by mutableStateOf(null)
        private set

    var previsoes by mutableStateOf(emptyList<Previsao>())
        private set

    init {
        viewModelScope.launch {
            previsoes = repositoryPrevisao.getTempoHorario(cidade)
            previsao = repositoryPrevisao.getTempoCidade(cidade)
        }
    }
}