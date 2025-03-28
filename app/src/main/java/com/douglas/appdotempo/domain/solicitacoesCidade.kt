package com.douglas.appdotempo.domain

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun main(){
    val cliente = HttpClient(CIO)
    val respond: HttpResponse = cliente.get("https://countriesnow.space/api/v0.1/countries")
    println(respond.status)
    val stringBody: String = respond.body()
    println(stringBody)
    cliente.close()
}