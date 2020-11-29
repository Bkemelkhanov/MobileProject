package com.example.projectmobilerestaurant.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantInfo(
    val phone: String? = null,
    val price: String? = null,
    val hours: String? = null,
    val paymentOptions: String? = null,
    val parking: String? = null,
    val description: String? = null
) : Parcelable