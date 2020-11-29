package com.example.projectmobilerestaurant.ui.reservations

import com.example.projectmobilerestaurant.core.utils.TableReservation
import com.example.projectmobilerestaurant.entities.Reservation
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ReservationsPresenter @Inject constructor(
    private val tableReservation: TableReservation
) : ReservationsContract.Presenter {

    private var view: ReservationsContract.View? = null
    private val disposable = CompositeDisposable()

    override fun getReservation(): Reservation? = tableReservation.reservation

    override fun setView(view: ReservationsContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
        disposable.clear()
    }

    override fun resume() {
    }

    override fun pause() {
    }

}