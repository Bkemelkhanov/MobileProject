package com.example.projectmobilerestaurant.ui.details

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.entities.Restaurant
import com.example.projectmobilerestaurant.ui.details.menu.MenuFragment
import com.example.projectmobilerestaurant.ui.details.reserve.ReserveFragment
import com.example.projectmobilerestaurant.ui.details.reviews.ReviewsFragment

class RestaurantDetailsPagerAdapter(
    fm: FragmentManager,
    private val context: Context,
    private val restaurant: Restaurant
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mCurrentFragment: Fragment? = null

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> ReserveFragment.showReserve(restaurant)
            1 -> MenuFragment()
            else -> ReviewsFragment()
        }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? =
        when (position) {
            0 -> context.getString(R.string.restaurant_details_reserve)
            1 -> context.getString(R.string.restaurant_details_menu)
            else -> context.getString(R.string.restaurant_details_reviews)
        }

    fun getCurrentFragment(): Fragment? {
        return mCurrentFragment
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (getCurrentFragment() !== `object`) {
            mCurrentFragment = `object` as Fragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}