package com.musscoding.mycalorytracker.navigation

import androidx.navigation.NavController
import com.musscoding.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}