package com.douglas.appdotempo.ui.features.prevDetalhada

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.douglas.appdotempo.data.pegaTempo
import com.douglas.appdotempo.data.solicitacoesCidades
import com.douglas.appdotempo.domain.Previsao

class prevDetalhadaViewModel(
    private val repositoryCidade: solicitacoesCidades,
    private val repositoryPrevisao: pegaTempo,
    private val cidade: String
): ViewModel() {

    var previsao by mutableStateOf(null)
        private set

    var previsoes by mutableStateOf(emptyList<Previsao>())
        private set
}