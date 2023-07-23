package com.musscoding.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//this class will save one food that was tracked for a specific meal
@Entity
data class TrackedFoodEntity(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: String, //meal type (snack, lunch, dinner ..etc)
    val amount: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val calories: Int,
    @PrimaryKey val id: Int? = null
)
