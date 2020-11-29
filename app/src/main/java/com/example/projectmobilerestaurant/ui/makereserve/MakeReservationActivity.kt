package com.example.projectmobilerestaurant.ui.makereserve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.extensions.showToast
import com.example.projectmobilerestaurant.core.prepopulation.getOccasions
import com.example.projectmobilerestaurant.core.utils.DateTimeUtils
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.core.utils.PrefsStorage
import com.example.projectmobilerestaurant.core.utils.TableReservation
import com.example.projectmobilerestaurant.entities.Restaurant
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_make_reservation.*
import kotlinx.android.synthetic.main.layout_reservation_info.*
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class MakeReservationActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_RESTAURANT = "extra_restaurant"

        fun intent(
            context: Context,
            restaurant: Restaurant
        ): Intent = Intent(context, MakeReservationActivity::class.java).apply {
            putExtra(EXTRA_RESTAURANT, restaurant)
        }
    }

    @Inject
    lateinit var tableReservation: TableReservation

    @Inject
    lateinit var prefsStorage: PrefsStorage

    private var mAdapter: OccasionsAdapter? = null
    private lateinit var mRestaurant: Restaurant
    private val disposable = CompositeDisposable()
    private val dtf: DateTimeFormatter = DateTimeUtils.getTimeFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_reservation)
        intent?.apply {
            mRestaurant = getParcelableExtra(EXTRA_RESTAURANT) as Restaurant
        }
        GlideApp.with(this)
            .load(mRestaurant.image)
            .into(imageBackground)

        toolbar.title = mRestaurant.name

        textName.text = mRestaurant.name
        textTable.text = getString(
            R.string.make_reservation_table_for_people,
            tableReservation.numberOfPeople
        )
        textTime.text = dtf.format(tableReservation.time)

        textMonth.text = tableReservation.day.month.name
        textDayNumber.text = tableReservation.day.dayOfMonth.toString()
        textDay.text = tableReservation.day.dayOfWeek.name

        disposable.add(
            RxTextView.afterTextChangeEvents(editPhone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.editable().toString().isNotBlank()) {
                        showPhoneError(false)
                    }
                }, {
                    it.printStackTrace()
                })
        )

        mAdapter = OccasionsAdapter(getOccasions()) {

        }
        recyclerOccasions.apply {
            adapter = mAdapter
        }

        buttonLearnMore.setSafeOnClickListener {

        }
        buttonReserve.setSafeOnClickListener {
            if (editPhone.text.toString().isBlank()) {
                showPhoneError(true)
            } else {
                showToast(getString(R.string.title_success))
                tableReservation.makeReservation(mRestaurant)
                var reservations = prefsStorage.getUserReservations()
                prefsStorage.setUserReservations(++reservations)
            }
        }
        switchTextUpdates.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    private fun showPhoneError(show: Boolean) {
        when (show) {
            true -> {
                tilPhone.error = getString(R.string.make_reservation_error_phone_number)
            }
            false -> {
                tilPhone.error = ""
            }
        }
    }
}
