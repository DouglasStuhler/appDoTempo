package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Previsao

interface pegaTempo {

    suspend fun geTempoLatlong(latitude: Double, longitude: Double) : String
    suspend fun getTempoCidade(cidade: String) : String
}