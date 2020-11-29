package com.example.projectmobilerestaurant.core.extensions

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.projectmobilerestaurant.di.AppComponent

fun Fragment.getAppComponent(): AppComponent = this.requireActivity().getAppComponent()

fun Fragment.getDefaultRecyclerDivider(): DividerItemDecoration =
    this.requireContext().getDefaultRecyclerDivider()

fun Fragment.showToast(message: String?, long: Boolean = false) =
    this.requireContext().showToast(message, long)