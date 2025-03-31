package com.douglas.appdotempo.ui.features.prevgeral

import ListPaisesRoute
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.douglas.appdotempo.R
import com.douglas.appdotempo.data.LatLong
import com.douglas.appdotempo.data.pegaTempoImp
import com.douglas.appdotempo.data.solicitacoesCidadeImp
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.ui.UIEvent
import com.douglas.appdotempo.ui.components.cardCarrousel
import com.douglas.appdotempo.ui.components.labelDiaAtual
import com.douglas.appdotempo.ui.components.resumoPrev
import com.douglas.appdotempo.ui.theme.azul_dia
import com.douglas.appdotempo.ui.theme.azul_noite
import com.douglas.appdotempo.ui.theme.cinza_dia_nublado
import com.douglas.appdotempo.ui.theme.cinza_nuvem

@Composable
fun prevGeralScreen(
    navigateToPaises: () -> Unit,
    currentLocation: LatLong?
){
    val viewModel = viewModel<PrevGeralViewModel>{
        PrevGeralViewModel(
            repositoryPrevisao = pegaTempoImp(),
            repositoryCidade = solicitacoesCidadeImp(),
            initialLocation = currentLocation
        )
    }

    val previsao = viewModel.previsao
    val previsoes = viewModel.previsoes

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent){
                is UIEvent.Navigate<*> -> {
                    when (uiEvent.route){
                        is ListPaisesRoute -> {
                            navigateToPaises()
                        }
                    }
                }

                UIEvent.NavigateBack -> {}
                else -> {}
            }
        }
    }

    PainelPrevGeral(
        previsao = previsao?: previsao1,
        previsoes = previsoes,
        onClick = viewModel::onEvent
    )
}

@Composable
fun PainelPrevGeral(
    previsao: Previsao,
    previsoes: List<Previsao> = emptyList(),
    onClick: (PrevGeralEvent) -> Unit
){

    var corFundo = when(previsao.icon){
        "01d" -> azul_dia
        "02d" -> azul_dia
        "03d" -> cinza_nuvem
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
            .background(
                /*brush = Brush.verticalGradient(
                    colors = listOf(
                        corFundo,
                        Color.White
                    ),
                    startY = 0f,
                    endY = 3000f
                )*/
                color = corFundo
            )
            .fillMaxHeight()
    ) {
        labelDiaAtual(previsao.cidade, onClickGeral = onClick)
        resumoPrev(previsao)
        cardCarrousel(previsoes)
    }
}