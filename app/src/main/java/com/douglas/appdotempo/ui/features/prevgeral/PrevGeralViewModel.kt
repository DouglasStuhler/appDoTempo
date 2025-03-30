package com.douglas.appdotempo.ui.features.prevgeral

import ListPaisesRoute
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.douglas.appdotempo.MainActivity
import com.douglas.appdotempo.data.pegaTempo
import com.douglas.appdotempo.data.solicitacoesCidades
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PrevGeralViewModel (
    private val repositoryPrevisao: pegaTempo,
    private val repositoryCidade: solicitacoesCidades
): ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    @SuppressLint("MissingPermission")
    var latLong = MainActivity().getloc()
    var loc: List<Double>? = latLong?.let {
        listOf(it.latitude, it.longitude)
    }


    var previsao : Previsao? by mutableStateOf(null)
        private set

    var previsoes by mutableStateOf(emptyList<Previsao>())
        private set

    init {
        viewModelScope.launch {
            Log.d("teste", loc.toString())
            previsao = repositoryPrevisao.getTempoLatLong(loc?.get(0) ?: 0.0, loc?.get(1) ?: 0.0)
            Log.d("teste", loc.toString())
            previsoes = repositoryPrevisao.previsoesCidade(previsao!!.cidade)
        }
    }

    fun onEvent(event: PrevGeralEvent){
        when(event){
            is PrevGeralEvent.onClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.Navigate(ListPaisesRoute))
                }
            }
        }
    }
}