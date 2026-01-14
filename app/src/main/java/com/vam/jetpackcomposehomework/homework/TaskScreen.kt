package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun TaskScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<TaskViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    TaskScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    state: TaskState,
    onAction: (TaskAction) -> Unit
) {

    val windowClass = currentWindowAdaptiveInfo().windowSizeClass
    val sizeMultiplier = when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 1f
        WindowWidthSizeClass.MEDIUM -> 1.2f
        WindowWidthSizeClass.EXPANDED -> 1.5f
        else -> 1f
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.tasks) { task ->
            TaskCell(task = task, onAction = onAction, sizeMultiplier = sizeMultiplier)
        }
    }
}


@Preview(device = Devices.PIXEL_9)
@Composable
fun TaskScreenPhonePreview() {
    TaskScreen(
        state = TaskState(tasks = DummyData.tasks),
        onAction = {}
    )
}


@Preview(device = Devices.NEXUS_10)
@Composable
fun TaskScreenTabletPreview() {
    TaskScreen(
        state = TaskState(tasks = DummyData.tasks),
        onAction = {}
    )
}