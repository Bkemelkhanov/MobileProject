package com.example.projectmobilerestaurant.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.TableReservation
import com.example.projectmobilerestaurant.ui.details.reserve.BottomSheetListener
import com.example.projectmobilerestaurant.ui.details.reserve.TableInfoBottomSheetFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment(), BottomSheetListener {

    @Inject
    lateinit var tableReservation: TableReservation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textClearAll.setSafeOnClickListener {
            clearAll()
        }
        buttonChooseRestaurant.setSafeOnClickListener {
            startActivity(SearchRestaurantActivity.intent(requireContext()))
        }
        buttonChooseCity.setSafeOnClickListener {

        }
        buttonChooseTable.setSafeOnClickListener {
            TableInfoBottomSheetFragment().show(
                requireActivity().supportFragmentManager,
                TableInfoBottomSheetFragment.TAG_TABLE_INFO_BOTTOM_SHEET
            )
        }
        buttonSearch.setSafeOnClickListener {

        }
    }

    private fun updateTableInfo() {
        buttonChooseTable.text = getString(
            R.string.restaurant_reserve_table_info,
            tableReservation.numberOfPeople
        )
    }

    private fun clearAll() {
        buttonChooseRestaurant.text = getString(R.string.search_all_restaurants)
        buttonChooseCity.text = getString(R.string.title_almaty)
        buttonChooseTable.text = getString(R.string.search_default_table)
        tableReservation.clearReservation()
    }

    override fun onBottomSheetDismiss() {
        updateTableInfo()
    }

}

