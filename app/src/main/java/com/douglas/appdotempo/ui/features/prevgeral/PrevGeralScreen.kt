package com.douglas.appdotempo.ui.features.prevgeral

import ListPaisesRoute
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.domain.previsao3
import com.douglas.appdotempo.ui.UIEvent
import com.douglas.appdotempo.ui.components.cardCarrousel
import com.douglas.appdotempo.ui.components.labelDiaAtual
import com.douglas.appdotempo.ui.components.resumoPrev
import com.douglas.appdotempo.ui.theme.azul_dia

@Composable
fun prevGeralScreen(
    navigateToPaises: () -> Unit
){
    val viewModel = viewModel<PrevGeralViewModel>{
        PrevGeralViewModel()
    }

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

    painelPrevGeral(
        onClick = viewModel::onEvent
    )
}

@Composable
fun painelPrevGeral(
    onClick: (PrevGeralEvent) -> Unit
){
    Column (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        azul_dia,
                        Color.White
                    ),
                    startY = 0f,
                    endY = 3000f
                )
            )
            .fillMaxHeight()
    ) {
        labelDiaAtual("Uberlândia", onClickGeral = onClick)
        resumoPrev(previsao2)
        cardCarrousel(listOf(previsao1, previsao2, previsao3, previsao1, previsao2, previsao3, previsao2))
    }
}