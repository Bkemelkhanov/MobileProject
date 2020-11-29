package com.example.projectmobilerestaurant.ui.details.reviews

import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.utils.GlideApp
import com.example.projectmobilerestaurant.entities.Review
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_review.*

class ReviewItem(
    private val review: Review
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            textName.text = review.name
            textCity.text = textName.context.getString(
                R.string.reviews_city,
                review.city
            )
            textDate.text = review.time
            textNumberOfReviews.text = textName.context.resources.getQuantityString(
                R.plurals.reviews,
                review.numberOfReviews ?: 0,
                review.numberOfReviews
            )
            ratingBar.progress = review.rating ?: 0
            textReview.text = review.review

            GlideApp.with(textName.context)
                .load("ashykOrin")
                .placeholder(
                    AvatarGenerator.avatarImage(
                        textName.context,
                        200,
                        AvatarConstants.CIRCLE,
                        review.name
                    )
                )
                .into(imageAvatar)
        }
    }

    override fun getLayout(): Int = R.layout.item_review
}
