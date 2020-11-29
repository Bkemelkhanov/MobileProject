package com.example.projectmobilerestaurant.ui.makereserve

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.inflate
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_occasion.view.*

class OccasionsAdapter(
    private val items: List<String>,
    private val onItemClick: (occasion: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseRestaurantViewHolder(parent.inflate(R.layout.item_occasion))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val occasion = items[holder.adapterPosition]
        holder.itemView.apply {
            textOccasion.text = occasion
            setSafeOnClickListener {
                onItemClick.invoke(occasion)
            }
        }
    }

    class BaseRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
