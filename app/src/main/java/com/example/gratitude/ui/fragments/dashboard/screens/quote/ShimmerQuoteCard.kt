package com.example.gratitude.ui.fragments.dashboard.screens.quote

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerQuoteCard(modifier: Modifier = Modifier) {
    val shimmerColors = listOf(
        Color.DarkGray.copy(alpha = 0.4f),
        Color.Gray.copy(alpha = 0.2f),
        Color.DarkGray.copy(alpha = 0.4f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Card(
        backgroundColor = Color.Black,
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
        )
    }
}
