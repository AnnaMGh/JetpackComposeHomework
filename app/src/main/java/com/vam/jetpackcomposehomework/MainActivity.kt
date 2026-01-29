package com.vam.jetpackcomposehomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.vam.jetpackcomposehomework.homework.ListItemScreenRoot
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeHomeworkTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { testTagsAsResourceId = true }) { innerPadding ->
                    ListItemScreenRoot(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}