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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.*

@Composable
fun AffirmationDetailScreen(
    categoryId: String,
    categoryTitle: String,
    viewModel: AffirmationViewModel = hiltViewModel()
) {
    val affirmations = viewModel.affirmations
    var showDialog by remember { mutableStateOf(false) }
    var customText by remember { mutableStateOf("") }
    val context = LocalContext.current

    // 🗣️ TTS setup
    val tts = remember {
        TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                // You need a reference to TextToSpeech object
                // So use variable assignment instead
            }
        }
    }


    DisposableEffect(Unit) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    // 🚀 Fetch affirmations for selected category
    LaunchedEffect(categoryId) {
        viewModel.fetchAffirmations()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryTitle) },
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            items(affirmations, key = { it.id }) { affirmation ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    backgroundColor = Color(0xFF1E1E1E),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "\"${affirmation.text}\"",
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
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
                                Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = Color.White)
                            }
                        }
                    }
                }
            }
        }

        // ➕ Custom Add Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Affirmation") },
                text = {
                    OutlinedTextField(
                        value = customText,
                        onValueChange = { customText = it },
                        placeholder = { Text("Write something positive...") },
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
