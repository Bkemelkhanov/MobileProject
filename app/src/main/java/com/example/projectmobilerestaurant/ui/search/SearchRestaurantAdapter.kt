package com.example.projectmobilerestaurant.ui.search

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.inflate
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.entities.Restaurant
import kotlinx.android.synthetic.main.item_restaurant_search.view.*

class SearchRestaurantAdapter(
    private val onItemClick: (restaurant: Restaurant) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val originalList: MutableList<Restaurant> = mutableListOf()
    private val items: MutableList<Restaurant> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseRestaurantViewHolder(parent.inflate(R.layout.item_restaurant_search))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurant = items[holder.adapterPosition]
        holder.itemView.apply {
            textName.text = restaurant.name
            textDescription.text = restaurant.info?.description

            GlideApp.with(context)
                .load(restaurant.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imageRestaurant)

            setSafeOnClickListener {
                onItemClick.invoke(restaurant)
            }
        }
    }

    fun loadItems(newItems: List<Restaurant>) {
        items.clear()
        originalList.clear()

        items.addAll(newItems)
        originalList.addAll(newItems)
        notifyDataSetChanged()
    }

    fun search(query: String) {
        val filteredList = originalList.filter {
            (it.name?.contains(query, true) == true)
                    || (it.info?.description?.contains(query, true) == true)
        }
        items.clear()
        items.addAll(filteredList)
        notifyDataSetChanged()
    }

    fun default() {
        items.clear()
        items.addAll(originalList)

        notifyDataSetChanged()
    }

    class BaseRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
