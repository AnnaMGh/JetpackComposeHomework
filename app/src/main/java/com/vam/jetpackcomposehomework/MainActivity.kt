package com.vam.jetpackcomposehomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.ui.Modifier
import com.vam.jetpackcomposehomework.homework.LocalSnackbarDemo
import com.vam.jetpackcomposehomework.homework.LocalSnackbarState
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeHomeworkTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = LocalSnackbarState.current) }) { innerPadding ->
                    LocalSnackbarDemo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
