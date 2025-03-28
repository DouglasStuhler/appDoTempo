package com.douglas.appdotempo.ui.components

import android.media.Image
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.*

@Composable
fun resumoPrev(
    previsao: Previsao
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .padding(20.dp)
        ){
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(6f)
                    /*.border(
                        width = 2.dp,
                        color = cinza_nuvem,
                        shape = RoundedCornerShape(20.dp)
                    )*/
                    /*.background(
                        color = transparente,
                        shape = RoundedCornerShape(20.dp)
                    )*/
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = previsao.tempAtual.toString()+"°",
                    fontSize = 100.sp,
                    color = branco_mais_claro
                )
                Image(
                    painter = painterResource(id = R.drawable.sol),
                    contentDescription = "teste",
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
                /*.border(
                    width = 2.dp,
                    color = cinza_nuvem,
                    shape = RoundedCornerShape(10.dp)
                )*/
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
                    text = previsao.chuvaPorc.toString()+"%",
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
                    text = previsao.umidade.toString()+"%",
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
                    text = previsao.velocidadeVento.toString()+"km/h",
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
            previsao = previsao2
        )
    }
}