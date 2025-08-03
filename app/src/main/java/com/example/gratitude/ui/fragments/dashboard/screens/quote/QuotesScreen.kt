package com.example.gratitude.ui.fragments.dashboard.screens.quote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gratitude.ui.fragments.dashboard.screens.home.HomeViewModel
import com.example.gratitude.ui.fragments.dashboard.screens.home.QuoteCard

@Composable
fun QuotesScreen(
    quoteViewModel: HomeViewModel = hiltViewModel()
) {
    val quotes = quoteViewModel.allQuotes.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        quoteViewModel.fetchAllQuotes()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Text(
            text = "✨ Words to light up your soul",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        if (quotes.value.isEmpty()) {
            Column {
                repeat(6) {
                    ShimmerQuoteCard(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(quotes.value.shuffled()) { index, quote ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(animationSpec = tween(400, delayMillis = index * 100)) +
                                slideInVertically(initialOffsetY = { it / 2 })
                    ) {
                        QuoteCard(quote)
                    }
                }
            }
        }
    }
}
