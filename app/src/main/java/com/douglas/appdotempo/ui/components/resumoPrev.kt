package com.douglas.appdotempo.ui.components

import android.media.Image
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.douglas.appdotempo.R
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.ui.features.prevgeral.PrevGeralEvent
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.*

@Composable
fun resumoPrev(
    previsao: Previsao,
    onClick: (PrevGeralEvent) -> Unit
){
    var tempAtual = previsao.tempAtual
    if(tempAtual.indexOf('.') != -1){
        tempAtual = tempAtual.substring(0, tempAtual.indexOf('.'))
    }

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
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(PrevGeralEvent.onClickCidade(previsao.cidade))
            }
    ) {
        Row (
            modifier = Modifier
                .padding(20.dp)
        ){
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(6f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = tempAtual+"°",
                    fontSize = 100.sp,
                    color = branco_mais_claro
                )
                Image(
                    painter = icone,
                    contentDescription = previsao.desc,
                    modifier = Modifier
                        .size(100.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Row (
            modifier = Modifier
                .padding(15.dp, 5.dp, 15.dp, 5.dp)
                .fillMaxWidth()
                .background(
                    color = branco_mais_claro,
                    shape = RoundedCornerShape(10.dp)
                ),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column (
                modifier = Modifier
                    .weight(3f)
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.prob_chuva),
                    contentDescription = "Probabilidade de chuva",
                    modifier = Modifier
                        .size(40.dp),
                    tint = azul_noite
                )
                Text(
                    text = previsao.chuvaPorc+"%",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column (
                modifier = Modifier
                    .weight(3f)
                    .padding(0.dp, 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.umidade),
                    contentDescription = "Umidade",
                    modifier = Modifier
                        .size(40.dp),
                    tint = azul_noite
                )
                Text(
                    text = previsao.umidade+"%",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column (
                modifier = Modifier
                    .weight(3f)
                    .padding(0.dp, 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.velocidade_vento),
                    contentDescription = "Velocidade do Vento",
                    modifier = Modifier
                        .size(40.dp),
                    tint = cinza_dia_nublado
                )
                Text(
                    text = previsao.velocidadeVento+"m/s",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }


}

@Composable
@Preview
fun resumoPrevPrev(){
    AppDoTempoTheme {
        resumoPrev(
            previsao = previsao2,
            onClick = {}
        )
    }
}