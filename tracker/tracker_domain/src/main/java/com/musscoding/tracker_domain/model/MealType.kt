package com.musscoding.tracker_domain.model

sealed class MealType(val name: String) {
    object Breakfast: MealType("breakfast")
    object Lunch: MealType("breakfast")
    object Dinner: MealType("breakfast")
    object Snack: MealType("breakfast")

    companion object {
        fun fromString(name: String): MealType {
            return when(name) {
                "breakfast" -> Breakfast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> Breakfast
            }
        }
    }
}