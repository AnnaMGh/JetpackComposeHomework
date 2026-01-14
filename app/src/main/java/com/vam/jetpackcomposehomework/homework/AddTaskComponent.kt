package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTaskComponent(
    modifier: Modifier = Modifier,
    task: Task,
    onAction: (TaskAction) -> Unit,
    sizeMultiplier: Float = 1f
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                modifier = modifier.fillMaxWidth(),
                value = task.title,
                onValueChange = { title -> onAction(TaskAction.OnNewTaskTitleChanged(title)) },
                maxLines = 2,
                placeholder = { Text(text = "Title", fontSize = 16.sp * sizeMultiplier) },
                textStyle = TextStyle(fontSize = 16.sp * sizeMultiplier)
            )

            TextField(
                modifier = modifier.fillMaxWidth(),
                value = task.description,
                onValueChange = { description ->
                    onAction(
                        TaskAction.OnNewTaskDescriptionChanged(
                            description
                        )
                    )
                },
                maxLines = 5,
                placeholder = { Text(text = "Description", fontSize = 16.sp * sizeMultiplier) },
                textStyle = TextStyle(fontSize = 16.sp * sizeMultiplier)
            )
        }

        Button(
            onClick = { onAction(TaskAction.OnNewTaskAdded) }) {
            Text(
                text = "Add", fontSize = 16.sp * sizeMultiplier
            )
        }
    }
}

@PreviewLightDark
@Preview(device = Devices.PIXEL_9)
@Composable
fun AddTaskPhonePreview() {
    AddTaskComponent(task = Task(), onAction = {})
}

@PreviewLightDark
@Preview(device = Devices.NEXUS_10)
@Composable
fun AddTaskTabletPreview() {
    AddTaskComponent(task = Task(), onAction = {}, sizeMultiplier = 1.5f)
}