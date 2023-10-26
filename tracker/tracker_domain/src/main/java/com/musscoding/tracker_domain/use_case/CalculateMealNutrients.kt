package com.musscoding.tracker_domain.use_case

import com.musscoding.core.domain.model.ActivityLevel
import com.musscoding.core.domain.model.Gender
import com.musscoding.core.domain.model.GoalType
import com.musscoding.core.domain.model.UserInfo
import com.musscoding.core.domain.preferences.Preferences
import com.musscoding.tracker_domain.model.MealType
import com.musscoding.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFood: List<TrackedFood>): Result {
        //we calculate here total nutrients for a specific day by meal
        val allNutrients = trackedFood
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        //here we wanna get all nutrients for a day without caring about meal type
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProteins = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val calogyGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (calogyGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (calogyGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (calogyGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = calogyGoal,
            totalCarbs = totalCarbs,
            totalProteins = totalProteins,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }

    //this method is to calculate how many calories a person burn on a specific day
    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67f * userInfo.age).roundToInt()
            }

            else -> {0}
        }
    }

    //this method is to calculate daily caloric requirements
    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
            else -> {0f}
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
            else -> {0}
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }
    //helper classes
    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    //helper class 2
    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProteins: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}