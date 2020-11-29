package com.example.projectmobilerestaurant.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.entities.Restaurant
import com.example.projectmobilerestaurant.ui.details.reserve.ReserveFragment
import com.example.projectmobilerestaurant.ui.details.reserve.BottomSheetListener
import kotlinx.android.synthetic.main.activity_restaurant_details.*

class RestaurantDetailsActivity : AppCompatActivity(), BottomSheetListener {

    companion object {
        private const val EXTRA_RESTAURANT = "extra_restaurant"

        fun intent(
            context: Context,
            restaurant: Restaurant
        ): Intent =
            Intent(context, RestaurantDetailsActivity::class.java).apply {
                putExtra(EXTRA_RESTAURANT, restaurant)
            }
    }

    private lateinit var restaurant: Restaurant
    private var fragmentAdapter: RestaurantDetailsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)
        intent.apply {
            restaurant = getParcelableExtra(EXTRA_RESTAURANT) as Restaurant
        }
        GlideApp.with(this)
            .load(restaurant.image)
            .into(imageBackground)

        fragmentAdapter = RestaurantDetailsPagerAdapter(
            supportFragmentManager,
            this,
            restaurant
        )
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onBottomSheetDismiss() {
        val reserveFragment = fragmentAdapter?.getCurrentFragment() as? ReserveFragment
        reserveFragment?.updateTableInfo()
    }
}

