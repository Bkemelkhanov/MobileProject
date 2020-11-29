package com.example.projectmobilerestaurant.ui.details.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getDefaultRecyclerDivider
import com.example.projectmobilerestaurant.core.extensions.toReviewItemList
import com.example.projectmobilerestaurant.core.prepopulation.getReviews
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_reviews, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(getReviews().toReviewItemList())
        }
        recyclerReviews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
            addItemDecoration(
                requireContext().getDefaultRecyclerDivider()
            )
        }
    }

}

