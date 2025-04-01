package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Previsao

interface pegaTempo {

    suspend fun getTempoLatLong(latitude: Double, longitude: Double) : Previsao?
    suspend fun getTempoCidade(cidadea: String) : Previsao?
    suspend fun previsoesCidade(cidadea: String) : List<Previsao>
    suspend fun getTempoHorario(cidadea: String) : List<Previsao>
}