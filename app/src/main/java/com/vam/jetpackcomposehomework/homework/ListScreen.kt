package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme


@Composable
fun ListScreen(modifier: Modifier = Modifier) {

    val viewModel = viewModel<ListViewModel>()
    val items by viewModel.items.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = viewModel.snackbarHostState) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            state = viewModel.lazyListState,
        ) {
            items(items) {
                Text(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                        .padding(16.dp),
                    text = it,
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}


@Preview
@Composable
fun ListScreenPreview() {
    JetpackComposeHomeworkTheme {
        ListScreen()
    }
}