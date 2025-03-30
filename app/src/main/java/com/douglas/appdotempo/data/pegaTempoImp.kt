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

class pegaTempoImp : pegaTempo {

    var api : String = "c0e53cc66fcae6540aa61e92c4441051"

    @SuppressLint("DefaultLocale")
    override suspend fun getTempoCidade(cidade: String) : Previsao?{
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
        val desc = jsonObj.getJSONObject("weather").getString("description")

        return Previsao(
            date = data_time,
            tempMax = temp_max,
            tempMin = temp_min,
            tempAtual = temp,
            umidade = umidade,
            chuvaPorc = chuva,
            velocidadeVento = vel_vento,
            desc = desc,
            cidade = cidade
        )
    }

    @SuppressLint("DefaultLocale")
    override suspend fun getTempoLatLong(latitude: Double, longitude: Double) : Previsao?{
        val cliente = HttpClient(CIO)
        val respond: HttpResponse =
            cliente.get("https://api.openweathermap.org/data/2.5/weather?lat=$latitude&lon=$longitude&appid=$api&lang=pt_br&units=metric")

        if(respond.status.value !in  200..299){
            print("Erro de requisição : ${respond.status}")

            return null
        }

        val respond1: HttpResponse =
            cliente.get("http://api.openweathermap.org/geo/1.0/reverse?lat=$latitude&lon=$longitude&appid=$api&lang=pt_br&limit=5")

        if(respond1.status.value !in  200..299){
            print("Erro de requisição : ${respond1.status}")
            return null
        }
        val jsonObj1 = JSONObject(respond1.bodyAsText())
        val city = jsonObj1.getString("name")

        cliente.close()

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
        val desc = jsonObj.getJSONObject("weather").getString("description")

        return Previsao(
            date = data_time,
            tempMax = temp_max,
            tempMin = temp_min,
            tempAtual = temp,
            umidade = umidade,
            chuvaPorc = chuva,
            velocidadeVento = vel_vento,
            desc = desc,
            cidade = city
        )
    }

    override suspend fun previsoesCidade(cidade: String) : List<Previsao>{
        var previsoes = mutableListOf<Previsao>()
        val cliente = HttpClient(CIO)
        val respond: HttpResponse =
            cliente.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=$cidade&appid=$api&lang=pt_br&units=metric&cnt=7")

        if(respond.status.value !in  200..299){
            print("Erro de requisição : ${respond.status}")
            return previsoes
        }

        cliente.close()

        val jsonObj = JSONObject(respond.bodyAsText())
        val list = jsonObj.getJSONArray("list")
        val tempList = mutableListOf<JSONObject>()

        //Adicionando os item do JSONArray em uma lista temporaria

        for (i in 0 until list.length()){
            tempList.add(list.getJSONObject(i))
        }

        tempList.forEach { elemento ->
            val weatherArray = elemento.getJSONArray("weather")
            val temp = elemento.getJSONObject("temp")
            val firstWeather = weatherArray.getJSONObject(0)

            val temp_day = temp.getString("day")
            val temp_min = temp.getString("min")
            val temp_max = temp.getString("max")
            val umidade = elemento.getString("humidity")
            val vel_vento = elemento.getString("speed")
            val chuva = elemento.getString("clouds")
            val desc = firstWeather.getString("description")

            previsoes.add(Previsao(
                date = "",
                tempMax = temp_max,
                tempMin = temp_min,
                tempAtual = temp_day,
                umidade = umidade,
                velocidadeVento = vel_vento,
                chuvaPorc = chuva,
                desc = desc,
                cidade = ""
            ))
        }

        return previsoes
    }

}

fun Locale.Companion.getDefault() {
    TODO("Not yet implemented")
}
