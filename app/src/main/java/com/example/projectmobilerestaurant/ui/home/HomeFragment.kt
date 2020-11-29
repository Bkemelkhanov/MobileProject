package com.example.projectmobilerestaurant.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.prepopulation.getAllCategories
import com.example.projectmobilerestaurant.core.prepopulation.getCuisines
import com.example.projectmobilerestaurant.core.prepopulation.getListOfRestaurantCoffeeAndBreakfast
import com.example.projectmobilerestaurant.core.utils.BaseItemDecoration
import com.example.projectmobilerestaurant.core.utils.PrefsStorage
import com.example.projectmobilerestaurant.ui.AccountInfoActivity
import com.example.projectmobilerestaurant.ui.createaccount.CreateAccountActivity
import com.example.projectmobilerestaurant.ui.details.RestaurantDetailsActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var prefsStorage: PrefsStorage

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textToolbar.text = getCurrentTime()

        imagePerson.setSafeOnClickListener {
            if (prefsStorage.getIsAuthenticated()) {
                startActivity(AccountInfoActivity.intent(requireContext()))
            } else {
                startActivity(CreateAccountActivity.intent(requireContext()))
            }
        }

        getAllCategories().forEach {
            val rv = RecyclerView(requireContext())
            rv.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                addItemDecoration(
                    BaseItemDecoration(
                        16, 8, 16, 8
                    )
                )
                adapter = BaseRestaurantAdapter(getListOfRestaurantCoffeeAndBreakfast()) {
                    requireActivity().startActivity(
                        RestaurantDetailsActivity.intent(
                            requireContext(),
                            it
                        )
                    )
                }
            }

            val tv = AppCompatTextView(requireContext()).apply {
                textSize = 20.0f
                text = it
                setTextColor(ContextCompat.getColor(requireContext(), R.color.ashykOrinBlack))
            }

            linearLayout.addView(tv)
            linearLayout.addView(rv)

            val params = tv.layoutParams as (LinearLayoutCompat.LayoutParams)
            params.setMargins(32, 16, 0, 16)
            tv.layoutParams = params

            val rvParams = rv.layoutParams as (LinearLayoutCompat.LayoutParams)
            rvParams.setMargins(0, 8, 0, 0)
            rv.layoutParams = rvParams
        }

        val cuisineText = AppCompatTextView(requireContext()).apply {
            textSize = 20.0f
            text = getString(R.string.home_browse_by_cuisine)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.ashykOrinBlack))
        }

        val rvCuisine = RecyclerView(requireContext())
        rvCuisine.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(
                BaseItemDecoration(
                    16, 8, 16, 8
                )
            )
            adapter = BaseCuisineAdapter(getCuisines()) {

            }
        }

        linearLayout.addView(cuisineText)
        linearLayout.addView(rvCuisine)

        val params = cuisineText.layoutParams as (LinearLayoutCompat.LayoutParams)
        params.setMargins(32, 16, 0, 16)
        cuisineText.layoutParams = params
    }

    private fun getCurrentTime(): String {
        val time = when (LocalDateTime.now().hour) {
            in 0..11 -> {
                "Good Morning"
            }
            in 12..15 -> {
                "Good Afternoon"
            }
            in 16..20 -> {
                "Good Evening"
            }
            in 21..23 -> {
                "Good Night"
            }
            else -> {
                "Good day, fella!"
            }
        }

        return if (prefsStorage.getIsAuthenticated()) {
            "$time, ${prefsStorage.getFirstName()}"
        } else {
            time
        }
    }

}

