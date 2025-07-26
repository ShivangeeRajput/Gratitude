package com.example.gratitude.ui.fragments.dashboard.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gratitude.ui.theme.GratitudeTheme
import com.example.gratitude.ui.viewmodels.FakeOnboardingSharedViewModel
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel

@Composable
fun HomeScreen(
    sharedViewModel: OnboardingSharedViewModel,
    quoteViewModel: HomeViewModel = hiltViewModel(),
    onAssistantClick: () -> Unit = {}
) {
    val userName by sharedViewModel.userName.observeAsState("")
    val quote by quoteViewModel.quote.observeAsState()

    LaunchedEffect(Unit) {
        quoteViewModel.fetchDailyQuote()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAssistantClick,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Gratitude Buddy",
                    tint = Color.White
                )
            }
        },
        backgroundColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Welcome back, ${userName.ifEmpty { "Friend" }} 👋",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Here’s your daily dose of gratitude 💖",
                style = MaterialTheme.typography.body2.copy(fontSize = 14.sp),
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            AnimatedVisibility(
                visible = quote != null,
                enter = fadeIn(animationSpec = tween(durationMillis = 600))
            ) {
                Card(
                    backgroundColor = Color(0xFF1E1E1E),
                    elevation = 12.dp,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "“${quote?.thought}”",
                            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "- ${quote?.author}",
                            style = MaterialTheme.typography.subtitle2.copy(
                                color = Color(0xFF64B5F6),
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }

            // Shimmer while loading
            if (quote == null) {
                ShimmerQuoteCard()
            }
        }
    }
}

@Composable
fun ShimmerQuoteCard() {
    val shimmerColors = listOf(
        Color.DarkGray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.3f),
        Color.DarkGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Card(
        backgroundColor = Color(0xFF2A2A2A),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeViewModel = FakeOnboardingSharedViewModel()
    GratitudeTheme {
        HomeScreen(sharedViewModel = fakeViewModel)
    }
}
