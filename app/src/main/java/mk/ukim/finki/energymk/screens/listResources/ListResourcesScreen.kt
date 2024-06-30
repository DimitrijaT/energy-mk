package mk.ukim.finki.energymk.screens.listResources

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mk.ukim.finki.energymk.common.components.ResourceComposableCard
import mk.ukim.finki.energymk.model.EnergyResource
import mk.ukim.finki.energymk.screens.main.EnergyViewModel


@Composable
fun ListResourcesScreen(
    modifier: Modifier = Modifier,
    category: String = "",
    filteredResources: List<EnergyResource> = emptyList(),
    navigateTo: (route: String, id: String) -> Unit,
) {
    LazyColumn {
        items(filteredResources, key =
        { resource -> resource.name }
        ) { resource ->
            ResourceComposableCard(navigateTo = navigateTo, energyResource = resource)
        }
    }
}