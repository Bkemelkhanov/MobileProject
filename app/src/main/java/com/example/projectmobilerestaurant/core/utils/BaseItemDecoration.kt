package com.example.projectmobilerestaurant.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BaseItemDecoration(
    private val mLeft: Int,
    private val mTop: Int,
    private val mRight: Int,
    private val mBottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
//            if (parent.getChildAdapterPosition(view) == 0) {
//                left = 0
//            }
            left = mLeft
            right = mRight

            top = mTop
            bottom = mBottom
        }
    }
}