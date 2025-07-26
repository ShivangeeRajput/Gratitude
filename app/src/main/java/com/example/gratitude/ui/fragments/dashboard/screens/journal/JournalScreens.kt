package com.example.gratitude.ui.fragments.dashboard.screens.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun JournalScreen(
    viewModel: JournalViewModel = hiltViewModel()
) {
    val journalList by viewModel.filteredJournalList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var editingEntry by remember { mutableStateOf<JournalEntry?>(null) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Journal") },
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                    actions = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                )
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.search(it.text)
                    },
                    placeholder = { Text("Search journals...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(50),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        backgroundColor = Color(0xFF1C1C1C),
                        placeholderColor = Color.LightGray,
                        cursorColor = Color(0xFFFF4081),
                        focusedBorderColor = Color(0xFFFF4081),
                        unfocusedBorderColor = Color.DarkGray
                    )
                )


            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingEntry = null
                    showDialog = true
                },
                backgroundColor = Color(0xFFD81B60),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Entry")
            }
        },
        backgroundColor = Color.Black
    ) { padding ->
        if (journalList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No journal entries yet.", color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.Black),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(journalList) { entry ->
                    JournalCard(
                        entry = entry,
                        onLongPress = {
                            editingEntry = entry
                            showDialog = true
                        },
                        onDelete = {
                            viewModel.deleteJournalEntry(entry)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        if (showDialog) {
            AddJournalDialog(
                initialText = editingEntry?.content ?: "",
                onDismiss = {
                    showDialog = false
                    editingEntry = null
                },
                onSave = { content ->
                    if (editingEntry != null) {
                        viewModel.updateJournalEntry(editingEntry!!.copy(content = content))
                    } else {
                        viewModel.insertJournalEntry(content)
                    }
                    showDialog = false
                    editingEntry = null
                }
            )
        }
    }
}
