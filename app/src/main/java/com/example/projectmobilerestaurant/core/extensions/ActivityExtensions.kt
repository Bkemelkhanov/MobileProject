package com.example.projectmobilerestaurant.core.extensions


import android.app.Activity
import com.example.projectmobilerestaurant.AshykOrinApplication
import com.example.projectmobilerestaurant.di.AppComponent

fun Activity.getAppComponent(): AppComponent =
    (this.application as AshykOrinApplication).appComponent