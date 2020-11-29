package com.example.projectmobilerestaurant.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.inflate
import com.example.projectmobilerestaurant.core.extensions.parsePriceType
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.DecimalFormatUtils
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.entities.Restaurant
import kotlinx.android.synthetic.main.item_base_restaurant.view.*
import java.text.DecimalFormat

class BaseRestaurantAdapter(
    private val items: List<Restaurant>,
    private val onItemClick: (restaurant: Restaurant) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dfSimple: DecimalFormat = DecimalFormatUtils.getDfSimple()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseRestaurantViewHolder(parent.inflate(R.layout.item_base_restaurant))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurant = items[holder.adapterPosition]
        holder.itemView.apply {
            textName.text = restaurant.name
            textPrice.text = restaurant.parsePriceType()
            textType.text = restaurant.restaurantType
            textFavNumber.text = context.getString(
                R.string.home_restaurant_reviews,
                dfSimple.format(restaurant.rating),
                restaurant.numberOfReviews.toString()
            )

            imageBookmark.setSafeOnClickListener {

            }

            GlideApp.with(context)
                .load(restaurant.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imageView)

            setSafeOnClickListener {
                onItemClick.invoke(restaurant)
            }
        }
    }

    class BaseRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
