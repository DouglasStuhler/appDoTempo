package com.douglas.appdotempo.ui.features.prevDetalhada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.domain.previsao3
import com.douglas.appdotempo.ui.components.LabelDetalhado
import com.douglas.appdotempo.ui.components.labelDiaAtual
import com.douglas.appdotempo.ui.components.quadroInfoDetalhada
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.azul_dia

@Composable
fun prevDetalhadaScreen(
    cidade: String,
    previsao: Previsao,
    previoesHora: List<Previsao>
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = azul_dia
            )
            .padding(10.dp)
    ) {
        LabelDetalhado(cidade, previsao)
        quadroInfoDetalhada(previsao, previoesHora)
    }
}

@Composable
@Preview
fun prevDetalhadaScreenPrev() {
    AppDoTempoTheme {
        prevDetalhadaScreen(
            cidade = "Uberlândia",
            previsao = previsao1,
            previoesHora = listOf(
                previsao1,
                previsao2,
                previsao3,
                previsao1,
                previsao2
            )
        )
    }
}