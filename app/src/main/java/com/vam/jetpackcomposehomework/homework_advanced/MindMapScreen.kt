package com.vam.jetpackcomposehomework.homework_advanced

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull


data class ConstraintsInDP(
    val maxWidth: Dp,
    val maxHeight: Dp,
)

data class MindMapItem(
    val content: @Composable () -> Unit = {},
    val itemConstraints: ConstraintsInDP,
    val offset: DpOffset
)

private data class ProcessedMindMapItem(
    val placeable: Placeable,
    val finalXPosition: Int,
    val finalYPosition: Int
)

@Composable
fun MindMapScreen(modifier: Modifier = Modifier) {
    val mindMapItems = remember {
        listOf(
            MindMapItem(
                content = { IncDecCompose() },
                itemConstraints = ConstraintsInDP(maxWidth = 200.dp, maxHeight = 200.dp),
                offset = DpOffset(x = (-400).dp, y = (-200).dp)
            ),

            MindMapItem(
                content = { MindMapToDo() },
                itemConstraints = ConstraintsInDP(
                    maxWidth = 300.dp,
                    maxHeight = 100.dp
                ),
                offset = DpOffset(0.dp, 0.dp)
            ),

            MindMapItem(
                content = { IncDecCompose() },
                itemConstraints = ConstraintsInDP(maxWidth = 200.dp, maxHeight = 200.dp),
                offset = DpOffset(x = 400.dp, y = 200.dp)
            ),

            MindMapItem(
                content = { IncDecCompose() },
                itemConstraints = ConstraintsInDP(maxWidth = 200.dp, maxHeight = 200.dp),
                offset = DpOffset(x = 700.dp, y = 100.dp)
            ),
        )
    }

    var mindMapOffset by remember {
        mutableStateOf(IntOffset.Zero)
    }
    LazyMindMap(
        items = mindMapItems, mindMapOffset = mindMapOffset, onDrag = { delta ->
            mindMapOffset += delta
        }, modifier = modifier
            .fillMaxSize()

    )
}

@Composable
fun MindMapToDo(
    title: String = "MindMap ToDo",
    description: String = "Description",
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = description)

        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked })
    }
}

@Composable
fun IncDecCompose(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", fontWeight = FontWeight.Bold, fontSize = 34.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { count++ }) {
                Text(text = "Inc")
            }
            Button(onClick = { count-- }) {
                Text(text = "Dec")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyMindMap(
    items: List<MindMapItem>,
    mindMapOffset: IntOffset = IntOffset.Zero,
    onDrag: (delta: IntOffset) -> Unit,
    itemModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    LazyLayout(
        modifier = modifier
            .draggable2D(
                state = rememberDraggable2DState { delta ->
                    onDrag(delta.round())
                }
            ),
        itemProvider = {
            object : LazyLayoutItemProvider {
                override val itemCount: Int
                    get() = items.size

                @Composable
                override fun Item(index: Int, key: Any) {
                    items[index].content()
                }
            }
        }
    ) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val visibleArea = IntRect(
            left = 0,
            top = 0,
            right = layoutWidth,
            bottom = layoutHeight
        )

        val visibleItems = items.fastMapIndexedNotNull { index, item ->
            val finalXPosition =
                (item.offset.x.roundToPx() + layoutWidth / 2 + mindMapOffset.x)
            val finalYPosition =
                (item.offset.y.roundToPx() + layoutHeight / 2 + mindMapOffset.y)

            val maxItemWidth = item.itemConstraints.maxWidth.roundToPx()
            val maxItemHeight = item.itemConstraints.maxHeight.roundToPx()

            val extendedItemBounds = IntRect(
                left = finalXPosition - maxItemWidth / 2,
                top = finalYPosition - maxItemHeight / 2,
                right = finalXPosition + 3 * (maxItemWidth / 2),
                bottom = finalYPosition + 3 * (maxItemHeight / 2)
            )

            if (visibleArea.overlaps(extendedItemBounds)) {
                val placeable = measure(
                    index = index,
                    constraints = Constraints(
                        minWidth = 0, minHeight = 0,
                        maxWidth = item.itemConstraints.maxWidth.roundToPx()
                            .coerceAtMost(layoutWidth),
                        maxHeight = item.itemConstraints.maxHeight.roundToPx()
                            .coerceAtMost(layoutHeight)
                    )
                ).first()
                ProcessedMindMapItem(
                    placeable = placeable,
                    finalXPosition = finalXPosition,
                    finalYPosition = finalYPosition
                )
            } else {
                null
            }
        }

        println("Visible item count: ${visibleItems.size}")

        layout(constraints.maxWidth, constraints.maxHeight) {
            visibleItems.fastForEach { item ->
                item.placeable.place(
                    x = item.finalXPosition - item.placeable.width / 2,
                    y = item.finalYPosition - item.placeable.height / 2
                )
            }
        }
    }
}


