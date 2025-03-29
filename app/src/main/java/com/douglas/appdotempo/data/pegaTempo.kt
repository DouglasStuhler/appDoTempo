package com.douglas.appdotempo.data

import android.annotation.SuppressLint
import androidx.compose.ui.text.intl.Locale
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.json.JSONObject
import com.douglas.appdotempo.domain.Previsao
import java.util.*
import java.text.SimpleDateFormat

class pegaTempo {

    var api : String = "c0e53cc66fcae6540aa61e92c4441051"

    suspend fun getJsonCidade(latitude: Double, longitude: Double) : String{
        val cliente = HttpClient(CIO)
        val respond: HttpResponse =
            cliente.get("https://api.openweathermap.org/data/2.5/weather?lat=$latitude&lon=$longitude&appid=$api&lang=pt_br&units=metric")

        if(respond.status.value !in  200..299){
           print("Erro de requisição : ${respond.status}")

            return ""
        }
        return respond.bodyAsText()
    }

    suspend fun getJsonCidade(cidade: String) : String{
        val cliente = HttpClient(CIO)
        val respond: HttpResponse =
            cliente.get("https://api.openweathermap.org/data/2.5/weather?q=$cidade&appid=$api&lang=pt_br&units=metric")

        if(respond.status.value !in  200..299){
            print("Erro de requisição : ${respond.status}")

            return ""
        }
        return respond.bodyAsText()
    }

    @SuppressLint("DefaultLocale")
    suspend fun criaI(cidade: String) : Previsao?{
        val cliente = HttpClient(CIO)
        val respond: HttpResponse =
            cliente.get("https://api.openweathermap.org/data/2.5/weather?q=$cidade&appid=$api&lang=pt_br&units=metric")

        if(respond.status.value !in  200..299){
            print("Erro de requisição : ${respond.status}")

            return null
        }

        val c = Calendar.getInstance()

        val data_time = String.format(
            "%02d/%02d/%04d %02d:%02d:%02d",
            c.get(Calendar.DAY_OF_MONTH),
            c.get(Calendar.MONTH) + 1,
            c.get(Calendar.YEAR),
            c.get(Calendar.HOUR_OF_DAY),
            c.get(Calendar.MINUTE),
            c.get(Calendar.SECOND)
        )

        val jsonObj = JSONObject(respond.bodyAsText())
        val main = jsonObj.getJSONObject("main")
        val temp_max = main.getString("temp_max")
        val temp_min = main.getString("temp_min")
        val temp = main.getString("temp")
        val umidade = main.getString("humidity")
        val vel_vento = jsonObj.getJSONObject("wind").getString("speed")
        val chuva = jsonObj.getJSONObject("clouds").getString("all")

        return Previsao(
            date = "data_time",
            tempMax = temp_max,
            tempMin = temp_min,
            tempAtual = temp,
            umidade = umidade,
            chuvaPorc = chuva,
            velocidadeVento = vel_vento,
        )

    }


}

fun Locale.Companion.getDefault() {
    TODO("Not yet implemented")
}
