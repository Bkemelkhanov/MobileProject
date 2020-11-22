package com.example.projectmobilerestaurant.core

import com.example.projectmobilerestaurant.core.view.BaseView

interface BasePresenter<T : BaseView> {

    fun setView(view: T)

    fun destroy()

    fun resume()

    fun pause()

}