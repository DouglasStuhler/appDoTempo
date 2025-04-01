package com.douglas.appdotempo.ui.features.prevDetalhada

import PrevRoute
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.douglas.appdotempo.data.pegaTempoImp
import com.douglas.appdotempo.data.solicitacoesCidadeImp
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.domain.previsao3
import com.douglas.appdotempo.ui.UIEvent
import com.douglas.appdotempo.ui.components.LabelDetalhado
import com.douglas.appdotempo.ui.components.labelDiaAtual
import com.douglas.appdotempo.ui.components.quadroInfoDetalhada
import com.douglas.appdotempo.ui.features.listaCidades.ListaCidadesViewModel
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.azul_dia
import com.douglas.appdotempo.ui.theme.azul_noite
import com.douglas.appdotempo.ui.theme.cinza_dia_nublado
import com.douglas.appdotempo.ui.theme.cinza_nuvem

@Composable
fun PrevDetalhadaScreen(
    cidade: String
) {
    val repository = solicitacoesCidadeImp()
    val repositoryPrev = pegaTempoImp()
    val viewModel = viewModel<prevDetalhadaViewModel>{
        prevDetalhadaViewModel(
            cidade = cidade,
            repositoryCidade = repository,
            repositoryPrevisao = repositoryPrev
        )
    }
    var previsao = viewModel.previsao
    var previsoes = viewModel.previsoes

    previsao?.let{
        var corFundo = when(previsao.icon){
            "01d" -> azul_dia
            "02d" -> azul_dia
            "03d" -> azul_dia
            "04d" -> cinza_dia_nublado
            "09d" -> cinza_dia_nublado
            "10d" -> cinza_dia_nublado
            "11d" -> cinza_dia_nublado
            "13d" -> cinza_dia_nublado
            "01n" -> azul_noite
            "02n" -> azul_noite
            "03n" -> azul_noite
            "04n" -> azul_noite
            "09n" -> cinza_nuvem
            "10n" -> cinza_nuvem
            "11n" -> cinza_nuvem
            "13n" -> cinza_nuvem
            else -> azul_dia
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = corFundo
                )
                .padding(10.dp)
        ) {

            LabelDetalhado(cidade, previsao)
            quadroInfoDetalhada(previsao, previsoes)
        }
    }

}

@Composable
@Preview
fun prevDetalhadaScreenPrev() {
    AppDoTempoTheme {
        PrevDetalhadaScreen(
            cidade = "Uberlândia",
        )
    }
}