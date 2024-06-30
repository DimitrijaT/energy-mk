package mk.ukim.finki.energymk.screens.detailedView

import androidx.collection.FloatList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import mk.ukim.finki.energymk.common.components.ResourceComposableCard
import mk.ukim.finki.energymk.model.EnergyResource
import mk.ukim.finki.energymk.screens.main.Screen
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.axis.Axis
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.text.TextComponent

@Composable
fun EnergyResourceDetailsScreen(
    energyResource: EnergyResource,
    navigateBack: (route: String) -> Unit
) {

    val refreshDataset = remember { mutableIntStateOf(0) }
    val modelProducer = remember { ChartEntryModelProducer() }
    val datasetForModel = remember { mutableStateListOf(listOf<FloatEntry>()) }
    val datasetLineSpec = remember { arrayListOf<LineChart.LineSpec>() }

    val scrollState = rememberChartScrollState()

    LaunchedEffect(key1 = refreshDataset.intValue) {
        datasetForModel.clear()
        datasetLineSpec.clear()
        var xPos = 0f
        val dataPoints = arrayListOf<FloatEntry>()
        datasetLineSpec.add(
            LineChart.LineSpec(
                lineColor = Green.toArgb(),
                lineBackgroundShader = DynamicShaders.fromBrush(
                    brush = Brush.verticalGradient(
                        listOf(
                            Green.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                            Green.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                        )
                    )
                ),
            )
        )

        val dateX = energyResource.timeline.keys // date as a string
        val priceY = energyResource.timeline.values // price as floats

        for (i in priceY.indices) {
            val element = dateX.elementAt(i)
            val dataPoint = energyResource.timeline[element]!!

            dataPoints.add(FloatEntry(xPos, dataPoint))
            xPos++
        }

        datasetForModel.add(dataPoints)

        modelProducer.setEntries(datasetForModel)

    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (energyResource.timeline.size > 1) {
                Column(modifier = Modifier.height(200.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        ProvideChartStyle {
                            Chart(
                                chart = LineChart(
                                    lines = datasetLineSpec,
//                                    spacingDp = 300f,
                                ),
                                chartModelProducer = modelProducer,

                                startAxis = rememberStartAxis(
                                    title = "Time",
                                    tickLength = 0.dp,
                                    valueFormatter = { value, _ ->
                                        value.toInt().toString()
                                    },
                                    itemPlacer = AxisItemPlacer.Vertical.default(
                                        maxItemCount = 6
                                    )
                                ),
                                bottomAxis = rememberBottomAxis(
                                    title = "Price",
                                    tickLength = 10.dp,
                                    valueFormatter = { value, _ ->
                                        energyResource.timeline.keys.elementAt(value.toInt())
                                    },
                                    guideline = null,
//                                    labelRotationDegrees = 45f,
//                                    sizeConstraint = Axis.SizeConstraint.TextWidth("DD.MM.YYYYYYY"),
                                ),
                                chartScrollState = scrollState,
                                isZoomEnabled = true,
                            )
                        }
                    }
                }
            }
            Text(
                text = energyResource.name, style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            Text(text = energyResource.getLatestKey(), style = MaterialTheme.typography.labelSmall)
            Text(
                text = energyResource.getLatestValue().toString() + " " + energyResource.unit,
                style = MaterialTheme.typography.labelMedium
            )
            Button(onClick = {
                navigateBack(Screen.ListEnergyResourcesScreen.route)
            }) {
                Text(text = "Back")
            }
        }


    }


}