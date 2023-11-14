package com.example.sensoryshifters;

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Record : Destination("record")
    object Save : Destination("save")
    object Gallery : Destination("gallery")
    object Traverse : Destination("traverse")
}