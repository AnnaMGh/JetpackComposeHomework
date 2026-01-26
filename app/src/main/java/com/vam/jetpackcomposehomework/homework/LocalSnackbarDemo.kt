package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@Composable
fun LocalSnackbarDemo(modifier: Modifier = Modifier) {
    val state = LocalSnackbarState.current
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                scope.launch { state.showSnackbar("Hello world!") }
            }) {
            Text("Click me")
        }
    }
}