package com.douglas.appdotempo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.douglas.appdotempo.R
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.branco_mais_claro

@Composable
fun LabelDetalhado(
    cidade: String,
    previsao: Previsao
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            Icon(
                painter = painterResource(id = R.drawable.localizacao),
                contentDescription = "Localização",
                modifier = Modifier
                    .size(30.dp),
                tint = branco_mais_claro
            )
            Text(
                text = cidade,
                fontSize = 30.sp,
                color = branco_mais_claro
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.sol_nuvem),
                contentDescription = "Sol com nuvem",
                modifier = Modifier
                    .weight(1f)
                    .size(120.dp)
            )
            Text(
                text = previsao.tempAtual.toString()+"°",
                modifier = Modifier
                    .weight(1f),
                fontSize = 80.sp,
                color = branco_mais_claro
            )
        }
    }
}

@Composable
@Preview
fun LabelDetalhadoPrev(){
    AppDoTempoTheme {
        LabelDetalhado(
            cidade = "Uberlândia",
            previsao = previsao1
        )
    }
}