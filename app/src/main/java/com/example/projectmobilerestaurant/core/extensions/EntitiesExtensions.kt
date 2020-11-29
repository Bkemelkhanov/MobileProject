package com.example.projectmobilerestaurant.core.extensions

import com.example.projectmobilerestaurant.entities.Menu
import com.example.projectmobilerestaurant.entities.Restaurant
import com.example.projectmobilerestaurant.entities.Review
import com.example.projectmobilerestaurant.ui.details.menu.MainMenuItem
import com.example.projectmobilerestaurant.ui.details.reviews.ReviewItem

fun List<Menu>.toMainMenuItem(): List<MainMenuItem> =
    this.map {
        MainMenuItem(it)
    }


fun List<Review>.toReviewItemList(): List<ReviewItem> =
    this.map {
        ReviewItem(it)
    }

fun Restaurant.parsePriceType(): String {
    val result: StringBuilder = StringBuilder("")
    (0..(this.priceType ?: 1)).forEach {
        result.append("$")
    }
    return result.toString()
}
