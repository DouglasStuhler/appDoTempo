package com.douglas.appdotempo.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalWithComputedDefaultOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.douglas.appdotempo.R
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.amarelo_sol
import com.douglas.appdotempo.ui.theme.azul_dia
import com.douglas.appdotempo.ui.theme.azul_noite
import com.douglas.appdotempo.ui.theme.branco_mais_claro
import com.douglas.appdotempo.ui.theme.cinza_dia_nublado
import com.douglas.appdotempo.ui.theme.cinza_nuvem

@Composable
fun card (
    previsao: Previsao
){
    val icone = when(previsao.icon){
        "01d" -> painterResource(id = R.drawable.sol)
        "02d" -> painterResource(id = R.drawable.sol_nuvem)
        "03d" -> painterResource(id = R.drawable.nuvem)
        "04d" -> painterResource(id = R.drawable.nublado)
        "09d" -> painterResource(id = R.drawable.chuva)
        "10d" -> painterResource(id = R.drawable.chuva)
        "11d" -> painterResource(id = R.drawable.chuva_trovao)
        "13d" -> painterResource(id = R.drawable.neve)
        "01n" -> painterResource(id = R.drawable.lua)
        "02n" -> painterResource(id = R.drawable.lua_nuvem)
        "03n" -> painterResource(id = R.drawable.nuvem)
        "04n" -> painterResource(id = R.drawable.nublado)
        "09n" -> painterResource(id = R.drawable.chuva)
        "10n" -> painterResource(id = R.drawable.chuva)
        "11n" -> painterResource(id = R.drawable.chuva_trovao)
        "13n" -> painterResource(id = R.drawable.neve)
        else -> painterResource(id = R.drawable.sol)
    }

    var corFundo = when(previsao.icon){
        "01d" -> azul_dia
        "02d" -> azul_dia
        "03d" -> cinza_dia_nublado
        "04d" -> cinza_dia_nublado
        "09d" -> cinza_dia_nublado
        "10d" -> cinza_dia_nublado
        "11d" -> cinza_dia_nublado
        "13d" -> cinza_dia_nublado
        "01n" -> azul_noite
        "02n" -> azul_noite
        "03n" -> cinza_nuvem
        "04n" -> cinza_nuvem
        "09n" -> cinza_nuvem
        "10n" -> cinza_nuvem
        "11n" -> cinza_nuvem
        "13n" -> cinza_nuvem
        else -> azul_dia
    }

    var tempMax = previsao.tempMax
    if(tempMax.indexOf('.') != -1){
        tempMax = tempMax.substring(0, tempMax.indexOf('.'))
    }

    var tempMin = previsao.tempMin
    if(tempMin.indexOf('.') != -1){
        tempMin = tempMin.substring(0, tempMin.indexOf('.'))
    }

    Card(
        modifier = Modifier
            .background(
                color = corFundo,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp)
            .width(80.dp),
        colors = CardColors(
            containerColor = corFundo,
            contentColor = amarelo_sol,
            disabledContentColor = amarelo_sol,
            disabledContainerColor = corFundo
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = tempMax + "°",
                color = branco_mais_claro,
                fontSize = 24.sp,
            )
            Text(
                text = tempMin + "°",
                color = branco_mais_claro,
                fontSize = 24.sp,
            )
            Image(
                painter = icone,
                contentDescription = previsao.desc,
                modifier = Modifier
                    .size(38.dp)
                    .padding(vertical = 3.dp),
            )
        }
    }
}

@Composable
@Preview
fun cardPrev(){
    AppDoTempoTheme {
        card(
            previsao = previsao1
        )
    }
}