package com.example.projectmobilerestaurant.core.utils

import android.content.Context
import com.example.projectmobilerestaurant.entities.User
import javax.inject.Inject

class PrefsStorage @Inject constructor(
    context: Context
) {

    private val prefs = context.getSharedPreferences(PREFS_STORAGE, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun getIsAuthenticated(fallback: Boolean = false): Boolean =
        prefs.getBoolean(PREFS_IS_AUTHENTICATED, fallback)

    fun setIsAuthenticated(value: Boolean) =
        editor.putBoolean(PREFS_IS_AUTHENTICATED, value).apply()

    fun setUser(value: User) {
        setFirstName(value.firstName)
        setSecondName(value.secondName)
        setPhone(value.phone)
        setEmail(value.email ?: "")
        setPassword(value.password)
    }

    fun getUser(): User =
        User(
            getFirstName(),
            getSecondName(),
            getPhone(),
            getEmail(),
            getPassword()
        )

    fun setFirstName(value: String) =
        editor.putString(PREFS_USER_FIRST_NAME, value).apply()

    fun getFirstName(): String =
        prefs.getString(PREFS_USER_FIRST_NAME, "") ?: ""

    fun setSecondName(value: String) =
        editor.putString(PREFS_USER_SECOND_NAME, value).apply()

    fun getSecondName(): String =
        prefs.getString(PREFS_USER_SECOND_NAME, "") ?: ""

    fun setPhone(value: String) =
        editor.putString(PREFS_USER_PHONE, value).apply()

    fun getPhone(): String =
        prefs.getString(PREFS_USER_PHONE, "") ?: ""

    fun setEmail(value: String) =
        editor.putString(PREFS_USER_EMAIL, value).apply()

    fun getEmail(): String =
        prefs.getString(PREFS_USER_EMAIL, "") ?: ""

    fun setPassword(value: String) =
        editor.putString(PREFS_USER_PASSWORD, value).apply()

    fun getPassword(): String =
        prefs.getString(PREFS_USER_PASSWORD, "") ?: ""

    fun getUserReservations(): Int =
        prefs.getInt(PREFS_USER_RESERVATIONS, 0)

    fun setUserReservations(value: Int) =
        editor.putInt(PREFS_USER_RESERVATIONS, value).apply()

    fun getUserReviews(): Int =
        prefs.getInt(PREFS_USER_REVIEWS, 0)

    fun setUserReviews(value: Int) =
        editor.putInt(PREFS_USER_REVIEWS, value).apply()

    fun getUserPhotos(): Int =
        prefs.getInt(PREFS_USER_PHOTOS, 0)

    fun setUserPhotos(value: Int) =
        editor.putInt(PREFS_USER_PHOTOS, value).apply()
}