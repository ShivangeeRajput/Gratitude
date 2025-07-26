package com.example.gratitude.ui.fragments.dashboard.screens.quote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gratitude.ui.fragments.dashboard.screens.home.HomeViewModel
import com.example.gratitude.ui.fragments.dashboard.screens.home.ShimmerQuoteCard

@Composable
fun QuotesScreen(
    quoteViewModel: HomeViewModel = hiltViewModel() // Reuse if you're storing all quotes
) {
    val quotes = quoteViewModel.allQuotes.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        quoteViewModel.fetchAllQuotes() // New method: loads full list
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        if (quotes.value.isEmpty()) {
            // Shimmer / Loading
            Column {
                repeat(6) {
                    ShimmerQuoteCard(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
