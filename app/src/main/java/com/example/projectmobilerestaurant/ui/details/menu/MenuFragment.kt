package com.example.projectmobilerestaurant.ui.details.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.toMainMenuItem
import com.example.projectmobilerestaurant.core.prepopulation.getMenus
import com.example.projectmobilerestaurant.entities.Menu
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        recyclerMenu.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
        ExpandableGroup(MenuExpandableItem("Dinner Menu"), false).apply {
            add(Section(getMenus().toMainMenuItem()))
            groupAdapter.add(this)
        }
        ExpandableGroup(MenuExpandableItem("Beverage Menu"), false).apply {
            add(Section(getMenus().toMainMenuItem()))
            groupAdapter.add(this)
        }
        ExpandableGroup(MenuExpandableItem("Dessert menu"), false).apply {
            add(Section(getMenus().toMainMenuItem()))
            groupAdapter.add(this)
        }
        ExpandableGroup(MenuExpandableItem("Private Parties Menu"), false).apply {
            add(Section(getMenus().toMainMenuItem()))
            groupAdapter.add(this)
        }
        ExpandableGroup(MenuExpandableItem("Daily Specials"), false).apply {
            add(Section(getMenus().toMainMenuItem()))
            groupAdapter.add(this)
        }
    }
}

