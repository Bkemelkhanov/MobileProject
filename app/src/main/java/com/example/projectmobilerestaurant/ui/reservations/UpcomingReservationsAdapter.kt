package com.example.projectmobilerestaurant.ui.reservations

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.inflate
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.DateTimeUtils
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.entities.Reservation
import kotlinx.android.synthetic.main.item_upcoming_reservation.view.*
import org.threeten.bp.format.DateTimeFormatter

class UpcomingReservationsAdapter(
    private val onItemClick: (reservation: Reservation) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Reservation> = mutableListOf()
    private val dtf: DateTimeFormatter = DateTimeUtils.getDateInFutureTimes()
    private val dtfTime: DateTimeFormatter = DateTimeUtils.getTimeFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseRestaurantViewHolder(parent.inflate(R.layout.item_upcoming_reservation))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = items[holder.adapterPosition]
        holder.itemView.apply {
            GlideApp.with(context)
                .load(reservation.restaurant?.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imagePlace)

            textName.text = reservation.restaurant?.name
            textTable.text = context.getString(
                R.string.make_reservation_table_for_people,
                reservation.numberOfPeople
            )
            textDate.text =
                "${dtf.format(reservation.date)} at ${dtfTime.format(reservation.dateTime)}"

            setSafeOnClickListener {
                onItemClick.invoke(reservation)
            }
        }
    }

    fun loadItems(newItems: List<Reservation>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class BaseRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
