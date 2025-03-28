package com.douglas.appdotempo.data

import android.util.Log
import com.douglas.appdotempo.domain.Pais
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Solicicatao(
    val error: Boolean,
    val msg: String,
    val data: List<Pais>
)

@Serializable
data class SolicicataoCidade(
    val error: Boolean,
    val msg: String,
    val data: List<String>
)

class solicitacoesCidadeImp(): solicitacoesCidades{


    override suspend fun getPaises(): List<Pais> {
        val cliente = HttpClient(CIO)
        val respond: HttpResponse = cliente.get("https://countriesnow.space/api/v0.1/countries")
        var dados = Solicicatao(true, "", listOf(Pais("","","", listOf())))
        if(respond.status.value in 200..299) {
            dados = Json.decodeFromString<Solicicatao>(respond.body())
        }

        cliente.close()
        return dados.data

    }

    override suspend fun getCidades(pais: String): List<String> {
        val cliente = HttpClient(CIO)
        val respond: HttpResponse = cliente.get("https://countriesnow.space/api/v0.1/countries/cities/q?country=$pais")
        var dados = SolicicataoCidade(true, "", listOf(""))
        if(respond.status.value in 200..299) {
            dados = Json.decodeFromString<SolicicataoCidade>(respond.body())
        }

        cliente.close()
        return dados.data
    }
}

