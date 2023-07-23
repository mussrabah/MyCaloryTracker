package com.musscoding.tracker_data.remote.dto

import com.squareup.moshi.Json

//Moshi Json(name: String) will take the image_front_thumb_url from the Json response
//and map it to the imageFrontThumbUrl
//and also for the productName
data class Product(
    @field:Json(name = "image_front_thumb_url")
    val imageFrontThumbUrl: String?,
    val nutriments: Nutriments,
    @field:Json(name = "product_name")
    val productName: String?
)
