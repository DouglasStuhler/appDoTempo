package com.douglas.appdotempo.ui.components

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.douglas.appdotempo.domain.Previsao
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.domain.previsao3
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardCarrousel(
    previsoes: List<Previsao>
){
    val state = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(state)
            .padding(20.dp, 15.dp)
    ) {
        previsoes.forEach{ prev ->
            card(
                previsao = prev
            )
            Spacer( modifier = Modifier.size(5.dp))
        }

    }
}

@Composable
@Preview
fun cardCarrouselPrev() {
    val prevs = listOf(
        previsao1,
        previsao2,
        previsao3,
        previsao1,
        previsao2,
        previsao3,
        previsao1
    )

    AppDoTempoTheme {
        cardCarrousel(
            previsoes = prevs
        )
    }
}