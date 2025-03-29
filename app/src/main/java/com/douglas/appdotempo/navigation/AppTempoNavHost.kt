

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.douglas.appdotempo.ui.features.listaPaises.ListaCidadesScreen
import com.douglas.appdotempo.ui.features.listaPaises.ListaPaisesScreen
import com.douglas.appdotempo.ui.features.prevgeral.prevGeralScreen
import kotlinx.serialization.Serializable

@Serializable
object PrevRoute

@Serializable
data class ListCidadeRoute(
    val nomePais: String
)

@Serializable
data class DetalhesCidadeRoute(
    val nomeCidade: String
)

@Serializable
object ListPaisesRoute

@Composable
fun AppTempoNavHost(

) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PrevRoute
    ) {
        composable<PrevRoute> {
            prevGeralScreen(
                navigateToPaises = {
                    navController.navigate(ListPaisesRoute)
                }
            )
        }

        composable<ListCidadeRoute> {backStackEntry ->
            val rotaCidadeRoute = backStackEntry.toRoute<ListCidadeRoute>()
            ListaCidadesScreen(
                nomePais = rotaCidadeRoute.nomePais,
                navigateToDetalhes= { nomeCidade ->
                    navController.navigate(DetalhesCidadeRoute(nomeCidade = nomeCidade))
                }
            )
        }

        composable<ListPaisesRoute> {
            ListaPaisesScreen (
                navigateToCidades = { nomePais ->
                    navController.navigate(ListCidadeRoute(nomePais = nomePais))
                }
            )
        }
    }
}