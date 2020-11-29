package com.example.projectmobilerestaurant.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalTime

@Parcelize
data class Restaurant(
    val id: String? = null,
    val name: String? = null,
    val cuisineList: List<Cuisine>? = null,
    val image: String? = null,
    val priceType: Int? = 0,
    val restaurantType: String,
    val info: RestaurantInfo? = null,
    val reserveTime: List<LocalTime>? = null,
    val rating: Double? = null,
    val numberOfReviews: Long? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
) : Parcelable