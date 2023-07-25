package com.musscoding.tracker_domain.use_case

//we created this class because the more use cases we have
//the more our view-model's constructor
//becomes messy, so the solution is to create this wrapper class
data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients
)
