package com.example.gratitude.ui.fragments.dashboard.screens.affirmations

import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gratitude.R
import com.example.gratitude.ui.fragments.dashboard.screens.affirmations.model.AffirmationCategory
import java.util.*

@Composable
fun AffirmationsScreen(
    viewModel: AffirmationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedCategory by remember { mutableStateOf<AffirmationCategory?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var customText by remember { mutableStateOf("") }

    val tts = remember {
        TextToSpeech(context, null).apply {
            language = Locale.US
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    val categories = listOf(
        AffirmationCategory("selflove", "Self Love 💖", R.drawable.self_lovee),
        AffirmationCategory("career", "Career Growth 🚀", R.drawable.productivity),
        AffirmationCategory("health", "Health & Wellness 🌱", R.drawable.health),
        AffirmationCategory("mindfulness", "Mindfulness 🧘‍♀️", R.drawable.mindfullness),
        AffirmationCategory("relationships", "Relationships 🤝", R.drawable.love)
    )

    val affirmations = viewModel.affirmations

    LaunchedEffect(selectedCategory?.id) {
        selectedCategory?.id?.let { viewModel.fetchAffirmations() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Choose Your Affirmation Path 🌟",
            color = Color.White,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(categories) { category ->
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = 6.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clickable { selectedCategory = category }
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = category.drawableRes),
                            contentDescription = category.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                                    )
                                )
                        )
                        Text(
                            text = category.title,
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        )
                    }
                }
            }

            if (selectedCategory != null) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Affirmations for ${selectedCategory?.title}",
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(affirmations) { affirmation ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color.DarkGray,
                        elevation = 8.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "\"${affirmation.text}\"",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                IconButton(onClick = {
                                    viewModel.toggleLike(affirmation.id)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Like",
                                        tint = if (viewModel.isLiked(affirmation.id)) Color.Red else Color.Gray
                                    )
                                }

                                IconButton(onClick = {
                                    val sendIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, affirmation.text)
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    ContextCompat.startActivity(context, shareIntent, null)
                                }) {
                                    Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
                                }

                                IconButton(onClick = {
                                    tts.speak(affirmation.text, TextToSpeech.QUEUE_FLUSH, null, null)
                                }) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "TTS", tint = Color.White)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        if (selectedCategory != null) {
            FloatingActionButton(
                onClick = { showDialog = true },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Affirmation")
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Affirmation to ${selectedCategory?.title}") },
                text = {
                    OutlinedTextField(
                        value = customText,
                        onValueChange = { customText = it },
                        placeholder = { Text("Type your affirmation...") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        if (customText.isNotBlank()) {
                            viewModel.addCustomAffirmation(customText.trim())
                            customText = ""
                            showDialog = false
                        }
                    }) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}


