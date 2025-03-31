package com.douglas.appdotempo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.douglas.appdotempo.R
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.azul_temp
import com.douglas.appdotempo.ui.theme.branco_mais_claro
import com.douglas.appdotempo.ui.theme.vermelho_temp

@Composable
fun CardResumoHora(
    hora: String,
    previsao: Previsao,
){
    var tempMax = previsao.tempMax
    if(tempMax.indexOf('.') != -1){
        tempMax = tempMax.substring(0, tempMax.indexOf('.'))
    }

    var tempMin = previsao.tempMin
    if(tempMin.indexOf('.') != -1){
        tempMin = tempMin.substring(0, tempMin.indexOf('.'))
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(80.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = branco_mais_claro,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.sol_nuvem),
            contentDescription = "Sol com nuvem",
            modifier = Modifier
                .weight(1f)
                .size(60.dp)
                .padding(vertical = 10.dp)
        )
        Column (
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = hora,
                fontSize = 20.sp
            )
        }
        Column (
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(

                text = tempMax.toString()+"°",
                fontSize = 18.sp,
                color = vermelho_temp
            )
            Text(
                text = tempMin.toString()+"°",
                fontSize = 18.sp,
                color = azul_temp
            )
        }
    }
}

@Composable
@Preview
fun CardResumoHoraPrev() {
    AppDoTempoTheme {
        CardResumoHora(
            hora = "9:00",
            previsao = previsao1,
        )
    }
}