package com.douglas.appdotempo.ui.components

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
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun labelDiaAtual(
    localidade: String,
){
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 5.dp),
            verticalAlignment = Alignment.Bottom
        ){
            Icon(
                painter = painterResource(id = R.drawable.localizacao),
                contentDescription = "Localidade",
                modifier = Modifier
                    .size(45.dp),
            )
            Text(
                text = localidade,
                fontSize = 30.sp
            )
        }

        val data = Date()
        val local = Locale("pt", "BR")
        val formato = SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", local)

        Text(
            text = formato.format(data),
            fontSize = 20.sp
        )
    }
}

@Composable
@Preview
fun labelDiaAtualPrev(){
    AppDoTempoTheme {
        labelDiaAtual(
            localidade = "Uberlândia"
        )
    }
}