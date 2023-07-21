package com.musscoding.core.util

sealed class UiEvent {
    //this class will hold all kinds of events
    //that we wanna send from our ViewModels to our Composable
    //ex: navigating to different screens, popping the back stack,
    //showing a snack-bar ..etc. Basically everything we will like
    //to do on the UI just once.(once because that's not a state)

    data class Navigate(val route: String): UiEvent()
    object NavigateUp: UiEvent()
}