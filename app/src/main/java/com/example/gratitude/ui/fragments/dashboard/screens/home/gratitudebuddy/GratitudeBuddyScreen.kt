package com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy.model.Message

@Composable
fun GratitudeBuddyScreen(
    navController: NavController,
    viewModel: GratitudeBuddyViewModel = hiltViewModel()
) {
    val messages = remember { mutableStateListOf<Message>() }
    var input by remember { mutableStateOf("") }
    val reply by viewModel.reply.observeAsState()
    val loading by viewModel.loading.observeAsState(false)

    LaunchedEffect(Unit) {
        messages.add(Message("Hi! I'm your Gratitude Buddy 😊\nHow are you feeling today?", isUser = false))
    }

    reply?.let {
        if (messages.lastOrNull()?.text != it) {
            messages.add(Message(it, isUser = false))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gratitude Buddy 🤖") },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        backgroundColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                reverseLayout = true
            ) {
                items(messages.reversed()) { msg ->
                    ChatBubble(message = msg.text, isUser = msg.isUser)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = input,
                    onValueChange = { input = it },
                    placeholder = { Text("Type something...") },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (input.isNotBlank()) {
                            messages.add(Message(input, isUser = true))
                            viewModel.sendMessage(input)
                            input = ""
                        }
                    },
                    enabled = !loading,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            color = if (isUser) Color(0xFF64B5F6) else Color(0xFF333333),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}
