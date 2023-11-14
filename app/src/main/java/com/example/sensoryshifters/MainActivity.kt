package com.example.sensoryshifters;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sensoryshifters.screens.GalleryScreen
import com.example.sensoryshifters.screens.GalleryViewModel
import com.example.sensoryshifters.screens.HomeScreen
import com.example.sensoryshifters.screens.RecordScreen
import com.example.sensoryshifters.screens.RecordViewModel
import com.example.sensoryshifters.screens.TraverseScreen
import com.example.sensoryshifters.screens.TraverseViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensoryShiftersTheme {
                MainNavHost()
            }
        }
    }
}

/**
 * Navigation for which screen to show.
 *
 * The
 */
@Composable
fun MainNavHost(navController : NavHostController = rememberNavController()) {
    var recordViewModel by remember { mutableStateOf(RecordViewModel()) }
    var galleryViewModel by remember { mutableStateOf(GalleryViewModel())}
    var traverseViewModel by remember { mutableStateOf(TraverseViewModel())}

    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(Destination.Record.route) {
            RecordScreen(
                viewModel = recordViewModel,
            )
        }
        composable(Destination.Gallery.route) {
            GalleryScreen(
                viewModel = galleryViewModel,
            )
        }
        composable(Destination.Traverse.route) {
            TraverseScreen(
                viewModel = traverseViewModel,
            )
        }
    }
}


