package com.example.projectmobilerestaurant.entities

data class Review(
    val name: String,
    var city: String? = null,
    var review: String? = null,
    var time: String? = null,
    var numberOfReviews: Int? = null,
    var rating: Int? = null
)