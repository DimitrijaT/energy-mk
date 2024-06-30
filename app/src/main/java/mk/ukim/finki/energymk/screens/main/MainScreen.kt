package mk.ukim.finki.energymk.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mk.ukim.finki.energymk.model.EnergyResource
import mk.ukim.finki.energymk.screens.detailedView.EnergyResourceDetailsScreen
import mk.ukim.finki.energymk.screens.listResources.ListResourcesScreen

sealed class Screen(val route: String) {
    object ListEnergyResourcesScreen : Screen("list")
    object EnergyResourceDetailsScreen : Screen("details")
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    category: String,
    viewModel: EnergyViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val resources = viewModel.resources.collectAsStateWithLifecycle(emptyList())
    val filteredResources =
        resources.value.filter { it.category == category }
    var rememberedResource = rememberSaveable { "oil_and_oil_derivatives" }

    NavHost(
        navController = navController,
        startDestination = Screen.ListEnergyResourcesScreen.route
    ) {
        composable(Screen.ListEnergyResourcesScreen.route) {
            ListResourcesScreen(
                filteredResources = filteredResources,
                category = category,
                navigateTo = { route, id ->
                    rememberedResource = id
                    navController.navigate(route)
                }
            )
        }
        composable(Screen.EnergyResourceDetailsScreen.route) {
            EnergyResourceDetailsScreen(
                energyResource = filteredResources.find { it.name == rememberedResource }!!,
                navigateBack = { route ->
                    navController.navigate(route)
                })
        }
    }
}

