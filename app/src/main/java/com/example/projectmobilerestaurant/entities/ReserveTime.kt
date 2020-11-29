package com.example.projectmobilerestaurant.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReserveTime(
    val reserveTime: String? = null
) : Parcelable