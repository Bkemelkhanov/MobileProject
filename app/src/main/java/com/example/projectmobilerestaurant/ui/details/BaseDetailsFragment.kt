package com.example.projectmobilerestaurant.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projectmobilerestaurant.entities.Restaurant

open class BaseDetailsFragment : Fragment() {

    companion object {
        private const val ARG_RESTAURANT = "arg_restaurant"

        fun showFragment(
            fragment: BaseDetailsFragment,
            restaurant: Restaurant
        ): BaseDetailsFragment = fragment.apply {
            arguments = Bundle().apply {
                putParcelable(ARG_RESTAURANT, restaurant)
            }
        }
    }

    lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurant = requireArguments().getParcelable(ARG_RESTAURANT)!!
    }
}