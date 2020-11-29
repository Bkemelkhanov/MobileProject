package com.example.projectmobilerestaurant.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cuisine(
    val id: String? = null,
    val name: String? = null,
    val image: String? = null,
    val restaurantList: List<Restaurant>? = null
) : Parcelable