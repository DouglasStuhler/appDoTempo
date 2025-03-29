package com.douglas.appdotempo.ui.features.prevgeral

import ListPaisesRoute
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PrevGeralViewModel: ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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