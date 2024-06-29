package mk.ukim.finki.energymk.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mk.ukim.finki.energymk.common.components.ResourceComposableCard
import mk.ukim.finki.energymk.ui.theme.EnergyMkTheme


@Composable
fun MainScreen(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: EnergyViewModel = hiltViewModel()
) {
    val resources = viewModel.resources.collectAsStateWithLifecycle(emptyList())

    LazyColumn {
        items(resources.value) { resource ->
            ResourceComposableCard(resource)
        }
    }

}


