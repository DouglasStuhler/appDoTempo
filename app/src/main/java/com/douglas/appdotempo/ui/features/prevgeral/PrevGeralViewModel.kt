package com.douglas.appdotempo.ui.features.prevgeral

import androidx.lifecycle.ViewModel
import com.douglas.appdotempo.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class PrevGeralViewModel: ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
}