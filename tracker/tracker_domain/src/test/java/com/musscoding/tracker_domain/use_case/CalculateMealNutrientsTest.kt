package com.musscoding.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.musscoding.core.domain.model.ActivityLevel
import com.musscoding.core.domain.model.Gender
import com.musscoding.core.domain.model.GoalType
import com.musscoding.core.domain.model.UserInfo
import com.musscoding.core.domain.preferences.Preferences
import com.musscoding.tracker_domain.model.MealType
import com.musscoding.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {
    //this is a unit test class to test our CalculateMealNutrients use case
    //btw unit tests are used to test our use cases

    //here we should create an instance of the use-case's class we want to test
    private lateinit var calculateMealNutrients: CalculateMealNutrients

    //the code here is ran before every single test case
    //that's why we annotate it with "@Before"
    //because we always have to start every test case with
    //a very initial setup
    @Before
    fun setUp() {
        //for that we want to always initialize our calculateMealNutrients instance here:
        val preferences = mockk<Preferences>(relaxed = true)
        every {
            preferences.loadUserInfo()
        } returns UserInfo(
            gender = Gender.Male,
            age = 24,
            weight = 85f,
            height = 185,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    //here we want to write our first test case function
    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }
        val result = calculateMealNutrients(trackedFoods)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Carbs for dinner properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }
        val result = calculateMealNutrients(trackedFoods)

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        val expectedCarbs = trackedFoods
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(expectedCarbs)
    }
}