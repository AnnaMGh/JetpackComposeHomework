package com.vam.jetpackcomposehomework.homework

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.vam.jetpackcomposehomework.ui.theme.JetpackComposeHomeworkTheme
import kotlin.math.sqrt


@Composable
fun TrianglesAnimation(
    modifier: Modifier = Modifier,
    width: Dp = 100.dp,
    colors: List<Color> = listOf(
        Color.Red,
        Color.Blue,
        Color.Green,
        Color.Yellow,
        Color.Cyan
    )
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        for (i in 0 until colors.size) {
            AnimatedTriangle(width, colors[i], (i + 1) * 360f)
        }
    }
}


@Composable
fun AnimatedTriangle(width: Dp, color: Color, offset: Float = 360f) {
    val transition = rememberInfiniteTransition(
        label = "infinite transition"
    )
    val ratio by transition.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 5000,
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Restart,
        ), label = "ratio animation"
    )

    Box(
        modifier = Modifier
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 2 / 3f)
                rotationZ = ratio * offset
            }
            .width(width)
            .height(width * (sqrt(3f) / 2f))
            .clip(TriangleShape)
            .background(color = color)

    )
}

object TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply {
            moveTo(x = size.width / 2f, y = 0f)
            lineTo(x = 0f, y = size.height)
            lineTo(x = size.width, y = size.height)
            close()
        }
        )
    }
}


@Preview
@Composable
fun TriangleAnimationPreview() {
    JetpackComposeHomeworkTheme {
        TrianglesAnimation()
    }
}
