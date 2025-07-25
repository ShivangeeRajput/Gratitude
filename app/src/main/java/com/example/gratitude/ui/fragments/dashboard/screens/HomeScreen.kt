package com.example.gratitude.ui.fragments.dashboard.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel
import com.example.gratitude.ui.theme.GratitudeTheme
import com.example.gratitude.ui.viewmodels.FakeOnboardingSharedViewModel

@Composable
fun HomeScreen(sharedViewModel: OnboardingSharedViewModel) {
    val userName by sharedViewModel.userName.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome back, ${userName.ifEmpty { "Friend" }} 👋",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Here’s your daily dose of gratitude 💖",
            style = MaterialTheme.typography.body1,
            color = Color.White
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

