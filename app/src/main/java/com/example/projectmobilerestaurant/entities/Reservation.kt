package com.example.projectmobilerestaurant.entities

import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime

data class Reservation(
    var restaurant: Restaurant? = null,
    var tableInfo: String? = null,
    var numberOfPeople: Int = 2,
    var date: OffsetDateTime = OffsetDateTime.now(),
    var dateTime: LocalTime = LocalTime.now()
)
