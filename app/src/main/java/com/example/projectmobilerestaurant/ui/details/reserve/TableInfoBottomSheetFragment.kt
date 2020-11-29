package com.example.projectmobilerestaurant.ui.details.reserve

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.TableReservation
import kotlinx.android.synthetic.main.fragment_table_info_bottom_sheet.*
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class TableInfoBottomSheetFragment : SuperBottomSheetFragment() {

    companion object {
        const val TAG_TABLE_INFO_BOTTOM_SHEET = "tag_table_info_bottom_sheet"
    }

    @Inject
    lateinit var tableReservation: TableReservation

    private var mListener: BottomSheetListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
        mListener = context as? BottomSheetListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_table_info_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateNumberOfPeople()
        cardPlus.setOnClickListener {
            if (tableReservation.numberOfPeople < 20) {
                tableReservation.numberOfPeople++
                updateNumberOfPeople()
            }
        }
        cardMinus.setOnClickListener {
            if (tableReservation.numberOfPeople > 1) {
                tableReservation.numberOfPeople--
                updateNumberOfPeople()
            }
        }

        buttonDone.setSafeOnClickListener {
            mListener?.onBottomSheetDismiss()
            dismiss()
        }

        wheelPicker.apply {
            itemTextSize = 45
            yearStart = OffsetDateTime.now().year
            yearEnd = OffsetDateTime.now().plusYears(1).year
            month = OffsetDateTime.now().month.value
            selectedDay = OffsetDateTime.now().dayOfMonth
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        mListener = null
        super.onDismiss(dialog)
    }

    private fun updateNumberOfPeople() {
        textNumberOfPeople.text = resources.getQuantityString(
            R.plurals.people,
            tableReservation.numberOfPeople,
            tableReservation.numberOfPeople
        )
    }
}

interface BottomSheetListener {
    fun onBottomSheetDismiss()
}
