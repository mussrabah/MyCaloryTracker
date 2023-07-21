package com.musscoding.core.domain.preferences

import com.musscoding.core.domain.model.ActivityLevel
import com.musscoding.core.domain.model.Gender
import com.musscoding.core.domain.model.GoalType
import com.musscoding.core.domain.model.UserInfo

interface Preferences {
    //in clean architecture we rely so much on interfaces aka. abstraction
    //because for example we don't want our view model to know which preference
    //we mean (shared preferences, data store or some other solutions)
    //following the dependency inversion principle

    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(type: GoalType)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)

    fun loadUserInfo(): UserInfo

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}