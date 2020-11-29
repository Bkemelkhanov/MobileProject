package com.example.projectmobilerestaurant.core.utils

import com.example.projectmobilerestaurant.entities.Reservation
import com.example.projectmobilerestaurant.entities.Restaurant
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableReservation @Inject constructor() {

    var numberOfPeople: Int = 2
    var day: OffsetDateTime = OffsetDateTime.now()
    var time: LocalTime = LocalTime.now()
    var reservation: Reservation? = null
        private set

    fun clearReservation() {
        numberOfPeople = 2
        day = OffsetDateTime.now()
        time = LocalTime.now()

        reservation = null
    }

    fun makeReservation(restaurant: Restaurant) {
        reservation = Reservation(
            restaurant,
            numberOfPeople = numberOfPeople,
            date = day,
            dateTime = time
        )
    }
}