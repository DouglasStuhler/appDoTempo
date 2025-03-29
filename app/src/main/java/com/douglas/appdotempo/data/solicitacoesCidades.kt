package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Pais
import kotlinx.coroutines.flow.Flow

interface solicitacoesCidades {
    suspend fun getPaises(): List<Pais>
    suspend fun getCidades(pais: String): List<String>
    suspend fun getCidadesComFiltro(pais: String, pesquisa: String): List<String>
}