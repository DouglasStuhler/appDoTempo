package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Pais

interface solicitacoesCidades {
    suspend fun getPaises(): List<Pais>
    suspend fun getCidades(pais: String): List<String>
}