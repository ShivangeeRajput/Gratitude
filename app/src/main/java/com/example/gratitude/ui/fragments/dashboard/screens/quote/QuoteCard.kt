package com.example.gratitude.ui.fragments.dashboard.screens.home

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
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

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

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

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    clipboardManager.setText(AnnotatedString("${quote.thought} — ${quote.author}"))
                    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        tint = Color.DarkGray
                    )
                }

                IconButton(onClick = {
                    shareQuote(context, quote)
                }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color.DarkGray
                    )
                }
            }
        }
    }
}

// 🔗 Share function
private fun shareQuote(context: Context, quote: DailyQuote) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_TEXT,
            "“${quote.thought}”\n- ${quote.author}\n#GratitudeApp 💖"
        )
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share quote via"))
}
