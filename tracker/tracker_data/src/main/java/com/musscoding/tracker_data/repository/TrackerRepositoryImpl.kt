package com.musscoding.tracker_data.repository

import com.musscoding.tracker_data.local.TrackerDao
import com.musscoding.tracker_data.mapper.toTrackableFood
import com.musscoding.tracker_data.mapper.toTrackedFood
import com.musscoding.tracker_data.mapper.toTrackedFoodEntity
import com.musscoding.tracker_data.remote.OpenFoodApi
import com.musscoding.tracker_domain.model.TrackableFood
import com.musscoding.tracker_domain.model.TrackedFood
import com.musscoding.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

//we've implemented the repository here instead of domain module
//because we don't want our use cases to access the implementation of our repository
//also this helps making testing easy by creating another class that inherits from
// TrackerRepository
class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                                it.nutriments.proteins100g * 4f +
                                    it.nutriments.fat100g * 9f
                        //we'll tolerate an error of 1%
                        //than we check if the food calories is calculated correctly
                        //if so, than we wanna include it in our searched food's list
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                                && !calculatedCalories.equals(0.0)
                    }
                    .mapNotNull { it.toTrackableFood() }
            )
        }catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map {entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}