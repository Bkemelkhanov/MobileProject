package com.example.projectmobilerestaurant.ui.details.menu

import com.example.projectmobilerestaurant.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_menu_expandable.*

class MenuExpandableItem(
    private val name: String
) : Item(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            textName.text = name
            imageArrow.setImageResource(getImage())
            rootView.setOnClickListener {
                expandableGroup.onToggleExpanded()
                imageArrow.setImageResource(getImage())
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_menu_expandable

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun getImage(): Int =
        if (expandableGroup.isExpanded) {
            R.drawable.ic_keyboard_arrow_up
        } else {
            R.drawable.ic_keyboard_arrow_down
        }
}
