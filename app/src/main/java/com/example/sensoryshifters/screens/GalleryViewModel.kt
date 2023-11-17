package com.example.sensoryshifters.screens

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sensoryshifters.data.RecordedPaths

class GalleryViewModel : ViewModel() {
    var paths : List<List<Location>> by mutableStateOf(listOf(listOf<Location>()))
    private set

    init {
        paths = getRecordedPaths();

    }

    private fun getRecordedPaths(): List<List<Location>> {
        // Temp paths
        return RecordedPaths().paths
    }


}