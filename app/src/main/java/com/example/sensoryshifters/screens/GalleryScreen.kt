package com.example.sensoryshifters.screens

import android.location.Location
import android.os.Parcel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Cap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.android.gms.maps.model.SquareCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GalleryScreen(viewModel: GalleryViewModel) {
    val paths = viewModel.paths

    // Implement your UI for the GalleryScreen here
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            paths.forEach { path ->
                PathCard(path, "10/11/23","Gåtur langs Mosesøen")
            }
        }
    }
}
@Composable
fun PathCard(locations: List<Location>, dateRecorded: String, description: String) {


    var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false))}

    // position for center of map
    val latLng = LatLng(locations[locations.size / 2].latitude, locations[locations.size / 2].longitude)
    val cameraPos = CameraPosition.fromLatLngZoom(latLng, 15f)
    val cameraPositionState = rememberCameraPositionState {
        position = cameraPos
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp) // Set desired height for the card
            .padding(8.dp)
            .clickable(
                true,
                onClick = {}
            ),
        shape = RoundedCornerShape(8.dp), // Apply rounded corners to the card
        border = BorderStroke(1.dp, Color.Gray), // Add a border
        elevation = 8.dp, // Add elevation for a shadow effect
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Date recorded
            Text(
                text = "Date Recorded: $dateRecorded",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.semantics { contentDescription = "Recorded on $dateRecorded" }
            )

            // Description
            Text(
                text = "Description: $description",
                modifier = Modifier.semantics { contentDescription = "Description: $description" }
            )

            // GoogleMap with Polyline
            GoogleMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Occupy available space
                //uiSettings = uiSettings
            ) {
                // Add a Polyline to the map using the list of locations
                Polyline(
                    points = locations.map { LatLng(it.latitude, it.longitude) },
                    color = MaterialTheme.colors.primary, // Set color for the polyline
                    visible = true,
                    width = 8f, // Set width of the polyline
                )
            }
        }
    }
}
