package com.musscoding.tracker_domain.use_case

import com.musscoding.tracker_domain.model.TrackableFood
import com.musscoding.tracker_domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchFood(
            query.trim(),
            page,
            pageSize
        )
    }
}