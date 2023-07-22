package com.musscoding.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
    /*
    * the purpose of this class is to hold all ui events that can happen on the
    * nutrient goal screen and keep view-model clean.
    * -------------------------
    * Q: Why didn't we did that in the previous screens like gender, age ..etc?
    * A: because those screens contains only 02 events at most so it would be
    * waste of time and energy to create such an event class
    * --------------------------
     */

    data class OnCarbRatioEnter(val ratio: String): NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio: String): NutrientGoalEvent()
    data class OnFatRatioEnter(val ratio: String): NutrientGoalEvent()
    object OnNextClick: NutrientGoalEvent()
}
