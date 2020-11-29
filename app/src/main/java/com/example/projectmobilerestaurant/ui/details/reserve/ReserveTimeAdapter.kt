package com.example.projectmobilerestaurant.ui.details.reserve

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.inflate
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.DateTimeUtils
import com.example.projectmobilerestaurant.entities.ReserveTime
import kotlinx.android.synthetic.main.item_reserve_time.view.*
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class ReserveTimeAdapter(
    private val items: List<LocalTime>,
    private val onItemClick: (reserveTime: LocalTime) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dtf: DateTimeFormatter = DateTimeUtils.getTimeFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseRestaurantViewHolder(parent.inflate(R.layout.item_reserve_time))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reserveTime = items[holder.adapterPosition]
        holder.itemView.apply {
            textTime.text = dtf.format(reserveTime)

            setSafeOnClickListener {
                onItemClick.invoke(reserveTime)
            }
        }
    }

    class BaseRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
