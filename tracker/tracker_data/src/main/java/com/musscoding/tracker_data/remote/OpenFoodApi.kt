package com.musscoding.tracker_data.remote

import com.musscoding.tracker_data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodApi {

    //this function is to search for a specific food in a page that we specify
    //page size indicates how much foods does the page contains
    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun searchFood(
        @Query(value = "search_terms") query: String,
        @Query(value = "page") page: Int,
        @Query(value = "page_size") pageSize: Int
    ): SearchDto

    companion object {
        const val BASE_URL = "https://dz.openfoodfacts.org/"
    }
}