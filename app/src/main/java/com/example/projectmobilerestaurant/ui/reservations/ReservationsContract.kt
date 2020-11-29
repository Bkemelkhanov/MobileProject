package com.example.projectmobilerestaurant.ui.reservations

import com.example.projectmobilerestaurant.core.BasePresenter
import com.example.projectmobilerestaurant.core.view.BaseView
import com.example.projectmobilerestaurant.entities.Reservation

interface ReservationsContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
        fun getReservation(): Reservation?
    }
}