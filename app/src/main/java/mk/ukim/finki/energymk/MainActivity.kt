package mk.ukim.finki.energymk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import mk.ukim.finki.energymk.ui.theme.EnergyMkTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnergyMkTheme {
                NavigationScreen()
            }
        }
    }
}




