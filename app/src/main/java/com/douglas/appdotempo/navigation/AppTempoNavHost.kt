package com.douglas.to_dolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.douglas.appdotempo.ui.features.prevgeral.prevGeralScreen
import kotlinx.serialization.Serializable

@Serializable
object PrevRoute

@Serializable
data class AddEditRoute(
    val id: Long? = null
)

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
                navigateToPrevFocused = { id ->
                    navController.navigate(AddEditRoute(id = id))
                }
            )
        }
    }
}