package com.douglas.appdotempo.ui.features.prevgeral

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.ui.components.labelDiaAtual
import com.douglas.appdotempo.ui.components.resumoPrev
import com.douglas.appdotempo.ui.theme.azul_dia

@Composable
fun prevGeralScreen(
    navigateToPrevFocused: (id: Long?) -> Unit
){
    painelPrevGeral()
}

@Composable
fun painelPrevGeral(){
    Column (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        azul_dia,
                        Color.White
                    ),
                    startY = 0f,
                    endY = 2000f
                )
            )
    ) {
        labelDiaAtual("Uberlândia")
        resumoPrev(previsao2)
    }
}