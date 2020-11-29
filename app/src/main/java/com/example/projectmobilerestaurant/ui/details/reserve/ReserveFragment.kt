package com.example.projectmobilerestaurant.ui.details.reserve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.makeDialerIntent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.core.utils.TableReservation
import com.example.projectmobilerestaurant.entities.Restaurant
import com.example.projectmobilerestaurant.ui.details.BaseDetailsFragment
import com.example.projectmobilerestaurant.ui.details.futuretimes.FutureTimesActivity
import com.example.projectmobilerestaurant.ui.details.reserve.TableInfoBottomSheetFragment.Companion.TAG_TABLE_INFO_BOTTOM_SHEET
import com.example.projectmobilerestaurant.ui.makereserve.MakeReservationActivity
import kotlinx.android.synthetic.main.fragment_reserve.*
import javax.inject.Inject

class ReserveFragment : BaseDetailsFragment() {

    companion object {
        fun showReserve(
            restaurant: Restaurant
        ): BaseDetailsFragment = showFragment(
            ReserveFragment(),
            restaurant
        )
    }

    @Inject
    lateinit var tableReservation: TableReservation

    private var mAdapter: ReserveTimeAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_reserve, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initData()

        GlideApp.with(requireContext())
            .load("https://miro.medium.com/max/1400/1*qYUvh-EtES8dtgKiBRiLsA.png")
            .into(imageGoogleMap)

        imageGoogleMap.setSafeOnClickListener {
            startActivity(GoogleMapActivity.intent(requireContext(), restaurant))
        }
    }

    override fun onResume() {
        super.onResume()
        updateTableInfo()
    }

    private fun initViews() {
        cardTableInfo.setSafeOnClickListener {
            TableInfoBottomSheetFragment().show(
                requireActivity().supportFragmentManager,
                TAG_TABLE_INFO_BOTTOM_SHEET
            )
        }
        callForTakeout.setSafeOnClickListener {
            requireContext().makeDialerIntent(restaurant.info?.phone ?: "")
        }
        cardFindFutureTimes.setSafeOnClickListener {
            startActivity(FutureTimesActivity.intent(requireContext(), restaurant))
        }
        buttonYes.setSafeOnClickListener {

        }
        buttonNo.setSafeOnClickListener {

        }

        mAdapter = ReserveTimeAdapter(
            restaurant.reserveTime ?: listOf()
        ) {
            tableReservation.time = it
            startActivity(
                MakeReservationActivity.intent(
                    requireContext(),
                    restaurant
                )
            )
        }

        recyclerTableTime.apply {
            adapter = mAdapter
        }
    }

    fun updateTableInfo() {
        textTableInfo?.text = getString(
            R.string.restaurant_reserve_table_info,
            tableReservation.numberOfPeople
        )
    }

    private fun initData() {
        textHelpPersonalize.text = getString(
            R.string.restaurant_reserve_build_taste_profile,
            restaurant.name
        )
        textPhone.text = restaurant.info?.phone
        textPrice.text = restaurant.info?.price
        textHours.text = restaurant.info?.hours
        textPaymentOptions.text = restaurant.info?.paymentOptions
        textParking.text = restaurant.info?.parking
        textDescription.text = restaurant.info?.description
    }
}

