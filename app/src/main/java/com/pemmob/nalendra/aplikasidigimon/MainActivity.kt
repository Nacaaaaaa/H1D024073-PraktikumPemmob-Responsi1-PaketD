package com.pemmob.nalendra.aplikasidigimon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pemmob.nalendra.aplikasidigimon.ui.navigation.AppNavigation
import com.pemmob.nalendra.aplikasidigimon.ui.theme.AplikasiDigimonTheme

// Entry point aplikasi Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Dukungan Edge to Edge UI
        setContent {
            // Konsep: Custom Theme (Material 3)
            AplikasiDigimonTheme {
                // Konsep: Material Design 3 Surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Konsep: Compose Navigation
                    AppNavigation()
                }
            }
        }
    }
}