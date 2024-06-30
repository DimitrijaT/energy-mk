package mk.ukim.finki.energymk.screens.detailedView

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import mk.ukim.finki.energymk.common.components.ResourceComposableCard
import mk.ukim.finki.energymk.model.EnergyResource
import mk.ukim.finki.energymk.screens.main.Screen

@Composable
fun EnergyResourceDetailsScreen(
    energyResource: EnergyResource,
    navigateBack: (route: String) -> Unit
) {
//    ResourceComposableCard(navigateTo = navigateBack, energyResource = energyResource)
    Button(onClick = {
        navigateBack(Screen.ListEnergyResourcesScreen.route)
    }) {
        Text(text = "Login")
    }

    Column {
        Text(text = "Energy Resource Details Screen")
        Text(text = energyResource.name)
        Text(text = energyResource.category)
        Text(text = energyResource.getLatestKey())
        Text(text = energyResource.getLatestValue().toString())
    }

}