package com.pemmob.nalendra.aplikasidigimon.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.pemmob.nalendra.aplikasidigimon.ui.viewmodel.DetailUiState
import com.pemmob.nalendra.aplikasidigimon.ui.viewmodel.DetailViewModel

// Konsep: Jetpack Compose, Material 3, MVVM
// Screen untuk menampilkan detail informasi Digimon
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    digimonId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    // Collect Flow dari ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Dipanggil saat komposisi pertama kali dengan LaunchedEffect (Konsep Coroutine Compose)
    LaunchedEffect(digimonId) {
        viewModel.fetchDigimonDetail(digimonId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Digimon Detail") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                // UI State: Loading
                is DetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                // UI State: Data / Success
                is DetailUiState.Success -> {
                    val digimon = state.digimon
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Gambar opsional (Menggunakan Coil, Null safety dengan let)
                        digimon.images?.firstOrNull()?.href?.let { imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = digimon.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Menampilkan Nama, Level, Tipe, Atribut
                        Text(
                            text = digimon.name ?: "Unknown",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Pemrosesan Collection/List dengan Null Safety dan Lambda (joinToString)
                        val level = digimon.levels?.joinToString { it.level ?: "" } ?: "Unknown"
                        val type = digimon.types?.joinToString { it.type ?: "" } ?: "Unknown"
                        val attribute = digimon.attributes?.joinToString { it.attribute ?: "" } ?: "Unknown"

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                DetailRow(label = "Level", value = level)
                                DetailRow(label = "Type", value = type)
                                DetailRow(label = "Attribute", value = attribute)
                            }
                        }
                    }
                }

                // UI State: Error
                is DetailUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.fetchDigimonDetail(digimonId) }) {
                            Text("Coba Lagi")
                        }
                    }
                }
            }
        }
    }
}

// Konsep: Jetpack Compose Component Modular
@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}