package com.example.gratitude.ui.fragments.dashboard.screens.quote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratitude.ui.fragments.dashboard.screens.home.models.DailyQuote

@Composable
fun QuoteCard(quote: DailyQuote) {
    val backgroundColors = listOf(
        Color(0xFFe1bee7), Color(0xFFb2ebf2), Color(0xFFc8e6c9),
        Color(0xFFffe0b2), Color(0xFFdcedc8), Color(0xFFf8bbd0)
    )
    val cardColor = remember { backgroundColors.random() }

    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = cardColor,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "“${quote.thought}”",
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "- ${quote.author}",
                style = MaterialTheme.typography.subtitle2.copy(
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            )
        }
    }
}
