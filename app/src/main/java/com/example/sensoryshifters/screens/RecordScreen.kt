package com.example.sensoryshifters.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@Composable
fun RecordScreen(viewModel: RecordViewModel, onNavigateToSave: () -> Unit) {
    val context = LocalContext.current

    val fusedLocationProvider : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MapScreen(viewModel, Modifier.height(Dp(400f)).fillMaxWidth())
        // Display current recording status
        Text(
            text = if (viewModel.isRecording) "Recording..." else "Not Recording",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Button to start/stop recording
        Button(
            onClick = {
                if (!viewModel.isRecording) {
                    viewModel.startRecording(fusedLocationProvider,context)
                } else {
                    viewModel.stopRecording()
                    onNavigateToSave()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = if (viewModel.isRecording) "Stop Recording" else "Start Recording")
        }
    }
}