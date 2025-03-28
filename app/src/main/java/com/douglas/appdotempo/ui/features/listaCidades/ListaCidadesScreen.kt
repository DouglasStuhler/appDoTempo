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
import com.douglas.appdotempo.ui.features.listaCidades.ListaCidadesEvents
import com.douglas.appdotempo.ui.features.listaCidades.ListaCidadesViewModel
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.to_dolist.navigation.ListPaisesRoute

@Composable
fun ListaCidadesScreen(
    nomePais: String,
    navigateToDetalhes: (nomePais: String) -> Unit
){
    val repository = solicitacoesCidadeImp()
    val viewModel = viewModel<ListaCidadesViewModel>{
        ListaCidadesViewModel(
            nomePais = nomePais,
            repository = repository
        )
    }
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent){
                is UIEvent.Navigate<*> -> {
                    when (uiEvent.route){
                        is ListPaisesRoute -> {
                            navigateToDetalhes(viewModel.nomeCidade)
                        }
                    }
                }

                UIEvent.NavigateBack -> {}
            }
        }
    }

    val cidades = viewModel.listaCidade
    ListaCidades(
        cidades = cidades,
        onClick = viewModel::onEvent
    )
}

@Composable
fun ListaCidades(
    cidades: List<String>,
    onClick: (ListaCidadesEvents) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Escolha uma Cidade",
            fontSize = 35.sp
        )
        LazyColumn {
            items(cidades.size){
                cidades.forEach { cidade ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable(
                                onClick = {
                                    onClick(ListaCidadesEvents.onClick(cidade))
                                }
                            ),
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Text(
                            text = cidade,
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun ListaCidadesPrev() {
    AppDoTempoTheme {
        ListaCidades(
            cidades = listOf("Uberlândia", "São Paulo", "Rio de Janeiro"),
            onClick = {}
        )
    }
}

