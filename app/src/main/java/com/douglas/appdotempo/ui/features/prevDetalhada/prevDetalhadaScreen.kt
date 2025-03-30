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
    var previsao = viewModel.previsao?: previsao1
    var previsoes = viewModel.previsoes

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = azul_dia
            )
            .padding(10.dp)
    ) {

        LabelDetalhado(cidade, previsao)
        quadroInfoDetalhada(previsao, previsoes)
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