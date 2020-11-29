package com.example.projectmobilerestaurant.ui.details.futuretimes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.DateTimeUtils
import com.example.projectmobilerestaurant.core.utils.TableReservation
import com.example.projectmobilerestaurant.entities.Restaurant
import com.example.projectmobilerestaurant.ui.details.reserve.ReserveTimeAdapter
import com.example.projectmobilerestaurant.ui.makereserve.MakeReservationActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import kotlinx.android.synthetic.main.activity_future_times.*
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class FutureTimesActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_RESTAURANT = "extra_restaurant"

        fun intent(
            context: Context,
            restaurant: Restaurant
        ): Intent =
            Intent(context, FutureTimesActivity::class.java).apply {
                putExtra(EXTRA_RESTAURANT, restaurant)
            }
    }

    @Inject
    lateinit var tableReservation: TableReservation

    private lateinit var mRestaurant: Restaurant
    private lateinit var mAdapter: ReserveTimeAdapter
    private val dtf: DateTimeFormatter = DateTimeUtils.getDateInFutureTimes()

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_future_times)

        intent.apply {
            mRestaurant = getParcelableExtra(EXTRA_RESTAURANT) as Restaurant
        }

        mvc.addDecorator(object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay?): Boolean {
                day?.let {
                    return it.date > LocalDate.now()
                } ?: return false
            }

            override fun decorate(view: DayViewFacade?) {
                view?.addSpan(DotSpan())
            }

        })

        imageBack.setSafeOnClickListener {
            finish()
        }

        textDate.text = dtf.format(OffsetDateTime.now())

        mvc.setOnDateChangedListener { widget, date, selected ->
            textDate.text = dtf.format(date.date)
            tableReservation.day = date.date.atStartOfDay()
                .toInstant(ZoneOffset.UTC)
                .atOffset(OffsetDateTime.now().offset)
        }

        mvc.state().edit()
            .setMinimumDate(LocalDate.now())
            .commit()

        mAdapter = ReserveTimeAdapter(
            mRestaurant.reserveTime ?: listOf()
        ) {
            tableReservation.time = it
            startActivity(MakeReservationActivity.intent(this, mRestaurant))
        }

        recyclerTableTime.apply {
            adapter = mAdapter
        }

    }
}
