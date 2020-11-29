package com.example.projectmobilerestaurant.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.prepopulation.getListOfRestaurantCoffeeAndBreakfast
import com.example.projectmobilerestaurant.ui.details.RestaurantDetailsActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search_restaurant.*

class SearchRestaurantActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()
    private var mAdapter: SearchRestaurantAdapter? = null

    companion object {
        fun intent(context: Context): Intent =
            Intent(context, SearchRestaurantActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_restaurant)

        imageBack.setSafeOnClickListener {
            finish()
        }

        disposable.add(
            RxTextView.afterTextChangeEvents(editSearch)
                .observeOn(AndroidSchedulers.mainThread())
                .skip(1)
                .subscribe({
                    val value = it.editable().toString()
                    if (value.isNotBlank()) {
                        mAdapter?.search(value)
                    } else {
                        mAdapter?.default()
                    }
                }, {
                    it.printStackTrace()
                })
        )

        mAdapter = SearchRestaurantAdapter {
            startActivity(
                RestaurantDetailsActivity.intent(
                    this,
                    it
                )
            )
        }
        mAdapter?.loadItems(getListOfRestaurantCoffeeAndBreakfast())

        recyclerSearchRestaurants.apply {
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchRestaurantActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

}