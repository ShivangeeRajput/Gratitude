package com.example.gratitude.ui.fragments.dashboard.screens.affirmations

import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.*

@Composable
fun AffirmationsScreen(
    viewModel: AffirmationViewModel = hiltViewModel()
) {
    val affirmations = viewModel.affirmations
    var showDialog by remember { mutableStateOf(false) }
    var customText by remember { mutableStateOf("") }
    val context = LocalContext.current

    // 🗣️ TextToSpeech instance

    val tts = remember {
        TextToSpeech(context, null).apply {
            language = Locale.US
        }
    }

    // Clean up TTS when composable leaves composition
    DisposableEffect(Unit) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    // Fetch affirmations when screen opens
    LaunchedEffect(Unit) {
        viewModel.fetchAffirmations()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFee9ca7), Color(0xFFffdde1))
                )
            )
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 100.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = affirmations,
                key = { it.id }
            ) { affirmation ->
                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = 10.dp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "\"${affirmation.text}\"",
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // ❤️ Like
                            IconButton(onClick = {
                                viewModel.toggleLike(affirmation.id)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Like",
                                    tint = if (viewModel.isLiked(affirmation.id)) Color.Red else Color.Gray
                                )
                            }

                            // 🔗 Share
                            IconButton(onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, affirmation.text)
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                ContextCompat.startActivity(context, shareIntent, null)
                            }) {
                                Icon(Icons.Default.Share, contentDescription = "Share")
                            }

                            // 🔊 Play Text-to-Speech
                            IconButton(onClick = {
                                tts.speak(
                                    affirmation.text,
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            }) {
                                Icon(Icons.Default.VolumeUp, contentDescription = "Listen")
                            }
                        }
                    }
                }
            }
        }

        // ➕ FAB to add custom affirmation
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }

        // ✏️ Custom Affirmation Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Your Affirmation") },
                text = {
                    OutlinedTextField(
                        value = customText,
                        onValueChange = { customText = it },
                        placeholder = { Text("Type something positive...") },
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
