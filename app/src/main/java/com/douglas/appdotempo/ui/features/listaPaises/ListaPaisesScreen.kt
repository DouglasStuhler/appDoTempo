package com.douglas.appdotempo.ui.features.listaPaises

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.douglas.appdotempo.data.solicitacoesCidadeImp
import com.douglas.appdotempo.domain.Pais
import com.douglas.appdotempo.domain.paises
import com.douglas.appdotempo.ui.UIEvent
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.to_dolist.navigation.ListCidadeRoute
import com.douglas.to_dolist.navigation.ListPaisesRoute

@Composable
fun ListaPaisesScreen(
    navigateToCidades: (nomePais: String) -> Unit
){
    val repository = solicitacoesCidadeImp()
    val viewModel = viewModel<ListaPaisesViewModel>{
        ListaPaisesViewModel(
            repository = repository
        )
    }
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent){
                is UIEvent.Navigate<*> -> {
                    when (uiEvent.route){
                        is ListCidadeRoute -> {
                            navigateToCidades(viewModel.nomePais)
                        }
                    }
                }

                UIEvent.NavigateBack -> {}
            }
        }
    }

    val paises = viewModel.listPais
    ListaPaises(
        paises = paises,
        onClick = viewModel::onEvent
    )
}

@Composable
fun ListaPaises(
    paises: List<Pais>,
    onClick: (ListaPaisesEvents) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Escolha um Pais",
            fontSize = 35.sp
        )
        LazyColumn {
            itemsIndexed(paises) { index, pais ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable(
                            onClick = {
                                onClick(ListaPaisesEvents.onClick(pais.country))
                            }
                        ),
                    horizontalArrangement = Arrangement.Center,

                    ){
                    Text(
                        text = pais.country,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }

}

@Composable
@Preview
fun listaPaisesPrev() {
    AppDoTempoTheme {
        ListaPaises(
            paises = paises,
            onClick = {}
        )
    }
}

