package com.douglas.appdotempo.domain

data class Previsao(
    val date: String,
    val tempMax: String,
    val tempMin: String,
    val tempAtual: String,
    val umidade: String,
    val velocidadeVento: String,
    val chuvaPorc: String,

)

var previsao1 = Previsao(
    date = "2025-03-22",
    tempMax = "33",
    tempMin = "22",
    tempAtual = "25",
    umidade = "8",
    velocidadeVento = "10F",
    chuvaPorc = "10",

)

var previsao2 = Previsao(
    date = "2025-03-23",
    tempMax = "30",
    tempMin = "18",
    tempAtual = "28",
    umidade = "80",
    velocidadeVento = "7.3F",
    chuvaPorc = "35",

)


var previsao3 = Previsao(
    date = "2025-03-24",
    tempMax = "25",
    tempMin = "15",
    tempAtual = "18",
    umidade = "90",
    velocidadeVento = "7.3F",
    chuvaPorc = "75",
)