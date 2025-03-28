package com.douglas.appdotempo.ui.components

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
    var icone = painterResource(id = R.drawable.sol)
    var corIcone = amarelo_sol
    var corFundo = azul_dia
    if(previsao.chuvaPorc > 50){
        icone = painterResource(id = R.drawable.chuva)
        corFundo = cinza_dia_nublado
    }

    if(previsao.date == "2025-03-23"){
        corFundo = azul_noite
        icone = painterResource(id = R.drawable.lua)
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
            contentColor = corIcone,
            disabledContentColor = corIcone,
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
                text = previsao.tempAtual.toString() + "°",
                color = branco_mais_claro,
                fontSize = 40.sp,
            )
            Image(
                painter = icone,
                contentDescription = "teste",
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