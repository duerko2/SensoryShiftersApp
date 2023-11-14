package com.example.sensoryshifters.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
    fun SaveScreen(viewModel: RecordViewModel) {
        var name by remember{mutableStateOf("")}
        var private by remember{ mutableStateOf(true)}
        Column(){
            TextField(
                value = name,
                onValueChange = { name=it },
                label={Text("Name of route")}
            )
            Text(text="private route")
            Switch(
                checked = private,
                onCheckedChange = {private=!private}
                )
            Button(onClick = {viewModel.saveRoute()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Save Route")
            }
            viewModel.recordingLocations.forEach(action = {
                Text(text = "Latitude: ${it.latitude}, Longitude: ${it.longitude}")
            })
        }



    }
