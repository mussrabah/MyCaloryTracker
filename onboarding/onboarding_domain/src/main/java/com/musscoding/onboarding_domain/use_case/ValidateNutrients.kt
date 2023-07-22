package com.musscoding.onboarding_domain.use_case

import com.musscoding.core.util.UiText
import com.musscoding.core.R

class ValidateNutrients {
    /*
    * this class is just a use case of the nutrient goal view-model
    * used to validate carbs, protein and fat ratio
    * (following the single responsibility principle from the SOLID principles)
    * and notify the view-model about the result
    * and send the ratio as integer
    * mentioned in the sealed class Result
     */
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String,
    ): Result {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        //check if any value is null
        if (carbsRatio == null || proteinRatio == null || fatRatio == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }
        if (carbsRatio + proteinRatio + fatRatio != 100) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }
        return Result.Success(
            carbsRatio / 100f,
            proteinRatio / 100f,
            fatRatio / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ): Result()
        data class Error(
            val message: UiText
        ): Result()
    }
}