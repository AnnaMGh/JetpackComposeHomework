package com.vam.jetpackcomposehomework.homework


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme

@Composable
fun LoadingBarAnimation(modifier: Modifier = Modifier) {

    var value by remember { mutableFloatStateOf(0f) }


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ValueDisplay(
            value = value,
            maxValue = 100f,
            unit = "%"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Button(onClick = { value = 10f }) {
                Text("10%")
            }
            Button(onClick = { value = 50f }) {
                Text("50%")
            }
            Button(onClick = { value = 90f }) {
                Text("90%")
            }
        }
    }

}

@Composable
fun ValueDisplay(
    modifier: Modifier = Modifier,
    value: Float,
    maxValue: Float,
    unit: String,
    color: Color = Color.Red,
    strokeWidth: Dp = 10.dp
) {

    val ratio by animateFloatAsState(
        targetValue = value,
        animationSpec = tween(3000),
        label = "ratio animation"
    )

    Box(
        modifier = modifier
            .padding(16.dp)
            .drawBehind {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = ratio * 360f / maxValue,
                    useCenter = false,
                    style = Stroke(width = strokeWidth.toPx()),
                )
            }
            .size(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${"%.1f".format(ratio)}$unit",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun LoadingBarAnimationPreview() {
    JetpackComposeHomeworkTheme {
        LoadingBarAnimation()
    }
}
