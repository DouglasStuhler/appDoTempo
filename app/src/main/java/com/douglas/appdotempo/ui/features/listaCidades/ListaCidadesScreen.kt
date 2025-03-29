package com.douglas.appdotempo.ui.features.listaPaises

import ListPaisesRoute
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    val cidades = viewModel.listaCidades
    val pesquisa = viewModel.pesquisa


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

                is UIEvent.EnviaDadosFiltroCidade -> {
                    //cidades = uiEvent.cidades
                }

                UIEvent.NavigateBack -> {}
            }
        }
    }


    ListaCidades(
        pesquisa = pesquisa,
        pais = nomePais,
        cidades = cidades,
        onClick = viewModel::onEvent,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ListaCidades(
    pesquisa: String,
    pais: String,
    cidades: List<String>,
    onClick: (ListaCidadesEvents) -> Unit,
    onEvent: (ListaCidadesEvents) -> Unit
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
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = pesquisa,
            onValueChange = { pesquisa ->
                onEvent(
                    ListaCidadesEvents.onEvent(pais, pesquisa)
                )
            },
            placeholder = {
                Text(text = "Nome da Cidade")
            }
        )
        LazyColumn {
            items(cidades){ cidade ->
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

@Composable
@Preview
fun ListaCidadesPrev() {
    AppDoTempoTheme {
        ListaCidades(
            pesquisa = "",
            pais = "Brasil",
            cidades = listOf("Uberlândia", "São Paulo", "Rio de Janeiro"),
            onClick = {},
            onEvent = {}
        )
    }
}

