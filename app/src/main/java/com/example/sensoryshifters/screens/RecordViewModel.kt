package com.example.sensoryshifters.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordViewModel() : ViewModel() {

    // for GPS
    val priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    val cancellationTokenSource = CancellationTokenSource()

    var isRecording: Boolean by mutableStateOf(false)
        private set

    var currentLocation : Location by mutableStateOf(Location("provider").apply {
        // Set default latitude and longitude
        latitude = 55.731098
        longitude = 12.404700
    })
    private set

    val recordingLocations = mutableListOf<Location>()

    private var locationUpdateJob: Job? = null







    fun startRecording(fusedLocationProvider: FusedLocationProviderClient, context : Context) {
        recordingLocations.clear() // Clear previous recorded locations when starting a new recording
        isRecording = true

        Log.d("Location", "Checking Location permissions")

        locationUpdateJob = viewModelScope.launch {
            getLocation(fusedLocationProvider,context)
        }
    }

    suspend fun getLocation(fusedLocationProvider: FusedLocationProviderClient,context: Context) = withContext(Dispatchers.IO){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return@withContext
        }





        while(isRecording){
            fusedLocationProvider.getCurrentLocation(priority,cancellationTokenSource.token)
                .addOnSuccessListener { location ->
                    Log.d("Location", "location is found: $location")
                    addLocation(location)
                    currentLocation=location
                }
            Log.d("Location", "Waiting 5 seconds")
            delay(1000)
            Log.d("Location","Finished Waiting")
        }

    }

    fun stopRecording() {
        isRecording = false
        locationUpdateJob?.cancel() // Stop location updates when recording ends
    }

    private fun addLocation(location: Location?) {
        location?.let {
            recordingLocations.add(it) // Add the received location to the recording
        }
    }

    fun saveRoute() {
        // Save to Firebase DB
    }
}
