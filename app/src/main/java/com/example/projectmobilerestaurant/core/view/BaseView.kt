package com.example.projectmobilerestaurant.core.view

import android.content.Context

interface BaseView {

    fun showMessage(message: String?)

    fun context(): Context

}