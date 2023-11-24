package com.example.sensoryshifters.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp


@Composable
fun SaveScreen(viewModel: RecordViewModel, navigatetToHome: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var private by remember { mutableStateOf(true) }




    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name input field with accessibility content description
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name of route") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .semantics {
                    contentDescription = "Enter name for the route"
                }
        )

        // Accessibility-friendly indicator for private route
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Private route",
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        private = !private
                    }
                    .semantics {
                        contentDescription = if (private) "Private route" else "Public route"
                    }
            )
            Switch(
                checked = private,
                onCheckedChange = { private = !private },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .semantics {
                        contentDescription = if (private) "Private" else "Public"
                    },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = colors.primary, // Use primary color for checked thumb
                    checkedTrackColor = colors.secondary, // Use secondary color for checked track
                    uncheckedThumbColor = colors.onSurface.copy(alpha = 0.4f), // Use onSurface color for unchecked thumb
                    uncheckedTrackColor = colors.onSurface.copy(alpha = 0.2f) // Use surface color for unchecked track
                ),
            )
        }

        // Save button with accessibility content description
        Button(
            onClick = { viewModel.saveRoute(navigatetToHome) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .semantics {
                    contentDescription = "Save route"
                }
        ) {
            Text(text = "Save Route")
        }

        // Accessibility improvements for recorded locations
        /*
        viewModel.recordingLocations.forEach { location ->
            Text(
                text = "Latitude: ${location.latitude}, Longitude: ${location.longitude}",
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .semantics {
                        contentDescription = "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                    }
            )
        }

         */
    }
}
