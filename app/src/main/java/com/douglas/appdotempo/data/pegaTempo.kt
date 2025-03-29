package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Previsao

interface pegaTempo {

    suspend fun getJsonCidade(latitude: Double, longitude: Double) : String
    suspend fun getJsonCidade(cidade: String) : String
    suspend fun criaI(cidade: String) : Previsao?
}