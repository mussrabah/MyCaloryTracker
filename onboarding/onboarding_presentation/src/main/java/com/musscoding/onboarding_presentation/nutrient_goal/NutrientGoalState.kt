package com.musscoding.onboarding_presentation.nutrient_goal

//since we have three text on the screen we don't wanna load the view-model with
//all the 03 states, so following clean architecture guidelines
//we'll put these 03 states here in a data class
data class NutrientGoalState(
    val carbsRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)
