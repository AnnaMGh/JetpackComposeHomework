package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TaskCell(
    modifier: Modifier = Modifier,
    task: Task,
    onAction: (TaskAction) -> Unit,
    sizeMultiplier: Float = 1f
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = task.title,
                fontSize = 16.sp * sizeMultiplier,
                fontWeight = FontWeight.SemiBold,
                textDecoration = if (task.isChecked) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                fontSize = 16.sp * sizeMultiplier,
                text = task.description,
                textDecoration = if (task.isChecked) TextDecoration.LineThrough else TextDecoration.None,
            )
        }

        Checkbox(
            modifier = Modifier.scale(sizeMultiplier),
            checked = task.isChecked,
            onCheckedChange = {
                onAction(
                    TaskAction.OnItemTaskChecked(task.id)
                )
            })
    }
}


@Preview(device = Devices.PIXEL_9)
@Composable
fun TaskPhonePreview() {
    TaskCell(
        task = DummyData.tasks[0],
        onAction = {}
    )
}


@Preview(device = Devices.NEXUS_10)
@Composable
fun TaskTabletPreview() {
    TaskCell(
        task = DummyData.tasks[1],
        onAction = {}
    )
}