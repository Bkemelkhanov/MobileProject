package com.example.projectmobilerestaurant.ui.reservations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.getDefaultRecyclerDivider
import com.example.projectmobilerestaurant.core.utils.TableReservation
import kotlinx.android.synthetic.main.fragment_reservations.*
import javax.inject.Inject

class ReservationsFragment : Fragment(), ReservationsContract.View {

    @Inject
    lateinit var presenter: ReservationsPresenter

    @Inject
    lateinit var tableReservation: TableReservation

    private lateinit var mAdapter: UpcomingReservationsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_reservations, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reservation = presenter.getReservation()
        mAdapter = UpcomingReservationsAdapter {

        }
        recyclerUpcomingReservations.apply {
            adapter = mAdapter
            addItemDecoration(
                getDefaultRecyclerDivider()
            )
        }

        if (reservation == null) {
            textUpcomingReservations.text = getString(
                R.string.reservations_upcoming,
                0
            )
        } else {
            textUpcomingReservations.text = getString(
                R.string.reservations_upcoming,
                1
            )
            mAdapter.loadItems(listOf(reservation))
        }
    }

    override fun showMessage(message: String?) {
    }

    override fun context(): Context = requireContext()

}
