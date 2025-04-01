package com.douglas.appdotempo.ui.features.prevgeral

import DetalhesCidadeRoute
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
import com.douglas.appdotempo.data.LatLong
import com.douglas.appdotempo.data.pegaTempo
import com.douglas.appdotempo.data.solicitacoesCidades
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@SuppressLint("MissingPermission")
class PrevGeralViewModel (
    private val repositoryPrevisao: pegaTempo,
    private val repositoryCidade: solicitacoesCidades,
    initialLocation: LatLong? = null
): ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var loc: LatLong? by mutableStateOf(initialLocation?: LatLong(55.45,37.37))
        private set

    var previsao: Previsao? by mutableStateOf(null)
        private set

    var previsoes by mutableStateOf(emptyList<Previsao>())
        private set


    var cidade by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            initialLocation?.let{ location ->
                updateLocation(location)
            }?: run {
                loc?.let{ defaultLocation ->
                    updateLocation(defaultLocation)
                }
            }
        }
    }

    fun updateLocation(newLocation: LatLong) {
        loc = newLocation
        viewModelScope.launch {
            try {
                newLocation.let { location ->
                    previsao = repositoryPrevisao.getTempoLatLong(location.latitude, location.longitude)

                    previsao?.cidade?.let { cidade ->
                        previsoes = repositoryPrevisao.previsoesCidade(cidade)
                    }
                }
            } catch (e: Exception) {

                _uiEvent.send(UIEvent.ShowError("Erro ao atualizar localização"))
            }
        }
    }

    fun onEvent(event: PrevGeralEvent){
        when(event){
            is PrevGeralEvent.onClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.Navigate(ListPaisesRoute))
                }
            }
            is PrevGeralEvent.onClickCidade -> {
                viewModelScope.launch {
                    cidade = event.cidade?: ""
                    _uiEvent.send(UIEvent.Navigate(DetalhesCidadeRoute(previsao!!.cidade)))
                }
            }
        }
    }
}