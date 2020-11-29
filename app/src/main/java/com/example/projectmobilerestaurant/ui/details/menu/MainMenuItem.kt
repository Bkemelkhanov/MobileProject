package com.example.projectmobilerestaurant.ui.details.menu

import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.entities.Menu
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_menu_main.*

class MainMenuItem(
    private val menu: Menu
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            textName.text = menu.main
            textDescription.text = menu.description
            textPrice.text = menu.price
        }
    }

    override fun getLayout(): Int = R.layout.item_menu_main
}
