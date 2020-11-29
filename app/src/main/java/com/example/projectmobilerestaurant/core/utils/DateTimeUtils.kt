package com.example.projectmobilerestaurant.core.utils

import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {

    fun getTimeFormatter(): DateTimeFormatter =
        DateTimeFormatter.ofPattern("HH:mm")

    fun getDateInFutureTimes(): DateTimeFormatter =
        DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
}