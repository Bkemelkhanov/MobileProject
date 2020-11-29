package com.example.projectmobilerestaurant.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.inflate
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.entities.Cuisine
import kotlinx.android.synthetic.main.item_base_cuisine.view.*

class BaseCuisineAdapter(
    private val items: List<Cuisine>,
    private val onItemClick: (cuisine: Cuisine) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseCuisineViewHolder(parent.inflate(R.layout.item_base_cuisine))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cuisine = items[holder.adapterPosition]
        holder.itemView.apply {
            textName.text = cuisine.name

            GlideApp.with(context)
                .asBitmap()
                .load(cuisine.image)
                .centerCrop()
                .into(imageCuisine)

            setSafeOnClickListener {
                onItemClick.invoke(cuisine)
            }
        }
    }

    class BaseCuisineViewHolder(view: View) : RecyclerView.ViewHolder(view)
}