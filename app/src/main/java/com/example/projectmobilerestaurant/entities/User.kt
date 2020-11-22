package com.example.projectmobilerestaurant.entities

data class User(
    val firstName: String,
    val secondName: String,
    val phone: String,
    var email: String? = null,
    val password: String,
    var reservations: Int = 0,
    var reviews: Int = 0,
    var photos: Int = 0
)
