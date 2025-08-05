package com.example.gratitude.ui.fragments.dashboard.screens.home.streak

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gratitude.R

@Composable
fun StreakScreen() {
    val backgroundColor = Color(0xFF121212)

    Scaffold(
        backgroundColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("My Streaks", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                backgroundColor = backgroundColor,
                elevation = 0.dp
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                backgroundColor = Color(0xFF1E1E1E),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_streak),
                        contentDescription = "Streak",
                        tint = Color(0xFFFFA726),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Current Streak", color = Color.Gray)
                    Text("2 days", style = MaterialTheme.typography.h6, color = Color.White)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StreakStat(title = "Best Streak", value = "4 days")
                StreakStat(title = "Total Entries", value = "9")
                StreakStat(title = "Total Days", value = "7")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("August 2025", color = Color.White)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Calendar coming soon...", color = Color.Gray)
        }
    }
}

@Composable
fun StreakStat(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(title, color = Color.Gray, style = MaterialTheme.typography.caption)
        Text(value, color = Color.White, style = MaterialTheme.typography.body1)
    }
}

