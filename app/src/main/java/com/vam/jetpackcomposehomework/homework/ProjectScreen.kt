package com.vam.jetpackcomposehomework.homework

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import com.vam.jetpackcomposehomework.R
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme


@Composable
fun ProjectScreen(projects: List<Project>, modifier: Modifier = Modifier) {

    val projects by rememberSaveable {
        mutableStateOf(projects)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(projects) { project ->
            ProjectComponent(project = project)
        }
    }
}

@Composable
fun ProjectComponent(project: Project, modifier: Modifier = Modifier) {

    val windowClass = currentWindowAdaptiveInfo().windowSizeClass
    val sizeMultiplier = when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 1f
        WindowWidthSizeClass.MEDIUM -> 1.2f
        WindowWidthSizeClass.EXPANDED -> 1.5f
        else -> 1f
    }
    val iconSize = 32.dp
    val padding = 16.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE87457), shape = RoundedCornerShape(5.dp))
            .clip(shape = RoundedCornerShape(5.dp))
            .padding(padding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(iconSize * sizeMultiplier),
                imageVector = ImageVector.vectorResource(R.drawable.outline_check_circle_24),
                contentDescription = null,
                tint = Color.White,
            )

            Text(
                modifier = Modifier.weight(1f),
                text = project.name,
                fontSize = 24.sp * sizeMultiplier,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier.size(iconSize * sizeMultiplier),
                imageVector = ImageVector.vectorResource(R.drawable.baseline_more_horiz_24),
                contentDescription = null,
                tint = Color.White
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = iconSize * sizeMultiplier + padding, top = padding),
            horizontalArrangement = Arrangement.spacedBy(padding),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = project.description,
                fontSize = 18.sp * sizeMultiplier,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        }


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding),
            text = project.date,
            fontSize = 18.sp * sizeMultiplier,
            fontWeight = FontWeight.Light,
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
}

@Preview(device = Devices.PIXEL_9)
@Composable
fun ProjectComponentPreview() {
    JetpackComposeHomeworkTheme {
        ProjectScreen(DummyData.projects)
    }
}


@Preview(device = Devices.NEXUS_10)
@Composable
fun ProjectComponentTabletPreview() {
    JetpackComposeHomeworkTheme {
        ProjectScreen(DummyData.projects)
    }
}