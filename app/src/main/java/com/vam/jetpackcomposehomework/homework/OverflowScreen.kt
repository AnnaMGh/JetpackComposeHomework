package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme


@Composable
fun OverflowScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<OverflowViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    OverflowScreen(
        modifier = modifier.fillMaxSize(),
        state.texts,
        viewModel::onAction
    )
}

@Composable
fun OverflowScreen(
    modifier: Modifier = Modifier,
    items: List<OverflowItem>,
    onAction: (OverflowAction) -> Unit
) {
    val windowClass = currentWindowAdaptiveInfo().windowSizeClass
    val sizeMultiplier = when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 1f
        WindowWidthSizeClass.MEDIUM -> 1.2f
        WindowWidthSizeClass.EXPANDED -> 1.5f
        else -> 1f
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) Color.Black else Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            OverflowLayout(
                item = item,
                mainContent = { MainContent(item, onAction, sizeMultiplier) },
                overflowContent = { OverflowContent(item.text, sizeMultiplier) },
            )
        }
    }
}

@Composable
fun MainContent(
    item: OverflowItem,
    onAction: (OverflowAction) -> Unit,
    sizeMultiplier: Float = 1f
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            text = item.text,
            fontSize = 16.sp * sizeMultiplier,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            maxLines = 1
        )

        if (item.isOverflowing) {
            IconButton(
                onClick = { onAction(OverflowAction.ToggleOverflow(item.id)) }) {
                Icon(
                    imageVector = if (item.isToggled) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (item.isToggled) "Hide overflow content" else "Show overflow content",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun OverflowContent(text: String, sizeMultiplier: Float = 1f) {
    Text(
        modifier = Modifier
            .background(Color.Yellow)
            .padding(16.dp),
        text = text,
        fontSize = 16.sp * sizeMultiplier,
        color = Color.Black,
    )
}

@Composable
fun OverflowLayout(
    item: OverflowItem,
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    overflowContent: @Composable () -> Unit
) {
    Layout(
        modifier = modifier
            .background(
                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.White,
                shape = RoundedCornerShape(5.dp)
            ),
        content = {
            mainContent()
            if (item.isOverflowing && item.isToggled) {
                overflowContent()
            }
        }
    ) { measurables, constraints ->
        val mainMeasurable = measurables[0]
        val mainPlaceable = mainMeasurable.measure(constraints)

        val overflowPlaceable = if (item.isOverflowing && item.isToggled) {
            measurables[1].measure(constraints)
        } else null

        val height = mainPlaceable.height + (overflowPlaceable?.height ?: 0)

        layout(constraints.maxWidth, height) {
            mainPlaceable.place(x = 0, y = 0)
            overflowPlaceable?.place(x = 0, y = mainPlaceable.height)
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true, device = Devices.PIXEL_9)
@Composable
fun OverflowLayoutPhonePreview() {
    JetpackComposeHomeworkTheme {
        OverflowScreen(items = DummyData.items, onAction = {})
    }
}

@PreviewLightDark
@Preview(
    showBackground = true,
    device = Devices.NEXUS_10
)
@Composable
fun OverflowLayoutTabletPreview() {
    JetpackComposeHomeworkTheme {
        OverflowScreen(items = DummyData.items, onAction = {})
    }
}