@file:OptIn(ExperimentalAnimationApi::class)

package mk.ukim.finki.energymk

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mk.ukim.finki.energymk.screens.listResources.ListResourcesScreen
import mk.ukim.finki.energymk.screens.main.MainScreen


@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    var title by rememberSaveable { mutableStateOf("Electricity") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = title, modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.headlineMedium,
                            maxLines = 1
                        )
                    }
                },
            )
        },

        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = "Navigation Icon"
                            )
                        },
                        unselectedContentColor = LocalContentColor.current.copy(
                            alpha = ContentAlpha.disabled
                        ),
//                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.OilAndOilDerivatives.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.OilAndOilDerivatives.route) {
                title = Screen.OilAndOilDerivatives.title
                MainScreen(category = Screen.OilAndOilDerivatives.route)
            }
            composable(Screen.Electricity.route) {
                title = Screen.Electricity.title
                MainScreen(category = Screen.Electricity.route)
            }
            composable(Screen.DistrictHeating.route) {
                title = Screen.DistrictHeating.title
                MainScreen(category = Screen.DistrictHeating.route)
            }
            composable(Screen.RenewableSources.route) {
                title = Screen.RenewableSources.title
                MainScreen(category = Screen.RenewableSources.route)
            }
        }
    }
}


sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val title: String = ""
) {
    object OilAndOilDerivatives :
        Screen(
            "oil_and_oil_derivatives", R.string.oil_and_oil_derivatives, Icons.Filled.WaterDrop,
            "Oil and Oil Derivatives"
        )

    object Electricity :
        Screen("electricity", R.string.electricity, Icons.Filled.Bolt, "Electricity")

    object DistrictHeating :
        Screen(
            "district_heating",
            R.string.districtHeating,
            Icons.Filled.LocalFireDepartment,
            "District Heating"
        )

    object RenewableSources :
        Screen(
            "renewable_sources",
            R.string.renewable_sources,
            Icons.Filled.WindPower,
            "Renewable Sources"
        )
}

val items = listOf(
    Screen.OilAndOilDerivatives,
    Screen.Electricity,
    Screen.DistrictHeating,
    Screen.RenewableSources
)