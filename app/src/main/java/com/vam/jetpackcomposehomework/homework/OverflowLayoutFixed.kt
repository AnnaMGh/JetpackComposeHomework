package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme


@Composable
fun OverflowLayoutFixedScreen(modifier: Modifier = Modifier) {

    var isOverflowing by remember { mutableStateOf(false) }

    OverflowLayoutCorrect(
        modifier = modifier,
        isOverflowing = isOverflowing,
        mainContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "This is a toggle section",
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                )
                IconButton(
                    onClick = {
                        isOverflowing = !isOverflowing
                    }
                ) {
                    Icon(
                        imageVector = if (isOverflowing) {
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        },
        overflowContent = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(16.dp),
                text = "Secret section",
                color = Color.Black

            )
        }
    )
}

@Composable
fun OverflowLayoutCorrect(
    modifier: Modifier = Modifier,
    isOverflowing: Boolean,
    mainContent: @Composable () -> Unit,
    overflowContent: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->

        val overflowPlaceables = if (isOverflowing) {
            val overflowMeasurables = subcompose("overflow_content", overflowContent)
            overflowMeasurables.map { it.measure(constraints) }
        } else emptyList()

        val mainMeasurables = subcompose("main_content", mainContent)
        val mainPlaceables = mainMeasurables.map {
            it.measure(constraints)
        }

        val maxMainPlaceableWidth = mainPlaceables.maxOfOrNull { it.width } ?: 0
        val maxMainPlaceableHeight = mainPlaceables.maxOfOrNull { it.height } ?: 0
        val maxOverflowPlaceableWidth = overflowPlaceables.maxOfOrNull { it.width } ?: 0
        val maxOverflowPlaceableHeight = overflowPlaceables.maxOfOrNull { it.height } ?: 0


        println("mainPlaceables count = ${mainPlaceables.size}")
        println("Measurable mainMaxHeight: $maxMainPlaceableHeight")
        mainPlaceables.forEach {
            println("Measurable heights: ${it.height}")
        }

        val width = maxOf(maxMainPlaceableWidth, maxOverflowPlaceableWidth)
        val height = maxMainPlaceableHeight + maxOverflowPlaceableHeight

        layout(width, height) {
            var y = 0
            mainPlaceables.fastForEach { placeable ->
                placeable.place(0, y)
                y += placeable.height
            }
            overflowPlaceables.forEach { placeable ->
                placeable.place(0, y)
                y += placeable.height
            }
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true, device = Devices.PIXEL_9)
@Composable
fun OverflowLayoutCorrectPhonePreview() {
    JetpackComposeHomeworkTheme {
        OverflowLayoutFixedScreen()
    }
}
