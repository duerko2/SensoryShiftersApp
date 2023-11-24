package com.example.sensoryshifters.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Background(){

    Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)// Circles towards the top
    )     {


    Box(
            modifier = Modifier
                .offset((-60).dp, (-50).dp)
                .clip(CircleShape)
                .size(200.dp)
                .background(color = MaterialTheme.colors.surface)
                .align(Alignment.TopStart)
        )

        Box(
            modifier = Modifier
                .offset(0.dp, (-50).dp)
                .clip(CircleShape)
                .size(250.dp)
                .background(color = MaterialTheme.colors.surface)
                .align(Alignment.TopCenter)
        )

        Box(
            modifier = Modifier
                .offset(60.dp, (-50).dp)
                .clip(CircleShape)
                .size(200.dp)
                .background(color = MaterialTheme.colors.surface)
                .align(Alignment.TopEnd)
        )

        // Boxes towards the bottom
        /*
        Box(
            modifier = Modifier
                .offset((-60).dp, 120.dp)
                .clip(CircleShape)
                .size(200.dp)
                .background(color = MaterialTheme.colors.primaryVariant)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.BottomStart)
        )

        Box(
            modifier = Modifier
                .offset(0.dp, 120.dp)
                .clip(CircleShape)
                .size(250.dp)
                .background(color = MaterialTheme.colors.primaryVariant)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.BottomCenter)
        )

        Box(
            modifier = Modifier
                .offset(60.dp, 120.dp)
                .clip(CircleShape)
                .size(200.dp)
                .background(color = MaterialTheme.colors.primaryVariant)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.BottomEnd)
        )

         */
    }
}