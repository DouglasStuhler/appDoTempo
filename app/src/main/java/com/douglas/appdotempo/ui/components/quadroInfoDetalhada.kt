package com.douglas.appdotempo.ui.components

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.domain.previsao3
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.azul_temp
import com.douglas.appdotempo.ui.theme.branco_mais_claro
import com.douglas.appdotempo.ui.theme.vermelho_temp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun quadroInfoDetalhada(
    previsao: Previsao,
    previsoesHora: List<Previsao>
) {
    Column(
        modifier = Modifier
            .background(
                color = branco_mais_claro,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(10.dp, 15.dp)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            val data = Date()
            val local = Locale("pt", "BR")
            val formato = SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", local)

            Text(
                text = formato.format(data),
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ){
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Umidade: ${previsao.umidade}",
                    fontSize = 18.sp
                )
                Text(
                    text = "Chuva: ${previsao.chuvaPorc}",
                    fontSize = 18.sp
                )
                Text(
                    text = "Vel. Vento: ${previsao.velocidadeVento}",
                    fontSize = 18.sp
                )
            }
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Max: "+previsao.tempMax.toString()+"°",
                    fontSize = 18.sp,
                    color = vermelho_temp
                )
                Text(
                    text = "Min: "+previsao.tempMin.toString()+"°",
                    fontSize = 18.sp,
                    color = azul_temp
                )

            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            previsoesHora.forEach { previsaoItem ->
                CardResumoHora(hora = "9:00"+"h", previsao = previsaoItem)
            }
        }
    }
}

@Composable
@Preview
fun quadroInfoDetalhadaPrev() {
    AppDoTempoTheme {
        quadroInfoDetalhada(
            previsao = previsao1,
            previsoesHora = listOf(
                previsao1,
                previsao2,
                previsao3,
                previsao1,
                previsao2
            )

        )
    }
}