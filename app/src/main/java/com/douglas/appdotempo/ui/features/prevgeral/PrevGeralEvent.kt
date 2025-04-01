package com.douglas.appdotempo.ui.features.prevgeral

interface PrevGeralEvent {
    data class onClick(val nomePais: String?): PrevGeralEvent
    data class onClickCidade(val cidade: String?): PrevGeralEvent
}