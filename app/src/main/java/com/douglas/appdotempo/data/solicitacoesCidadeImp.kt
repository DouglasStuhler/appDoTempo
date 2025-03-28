package com.douglas.appdotempo.data

import com.douglas.appdotempo.domain.Pais
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Solicicatao(
    val error: Boolean,
    val msg: String,
    val data: List<Pais>
)

var cliente = HttpClient(CIO)


suspend fun getPaises(): List<Pais> {

    var respond: HttpResponse = cliente.get("https://countriesnow.space/api/v0.1/countries")
    var dados = Solicicatao(true, "", listOf(Pais("","","", listOf())))
    if(respond.status.value in 200..299) {
        dados = Json.decodeFromString<Solicicatao>(respond.body())
        println(dados.data[0].country)
    }

    cliente.close()
    return dados.data

}