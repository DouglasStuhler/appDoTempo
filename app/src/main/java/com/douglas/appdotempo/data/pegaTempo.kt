package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Previsao

interface pegaTempo {

    suspend fun getTempoLatLong(latitude: Double, longitude: Double) : Previsao?
    suspend fun getTempoCidade(cidade: String) : Previsao?
    suspend fun previsoesCidade(cidade: String) : List<Previsao>
}