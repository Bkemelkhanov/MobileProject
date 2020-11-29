package com.example.projectmobilerestaurant.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.utils.PrefsStorage
import kotlinx.android.synthetic.main.activity_account_info.*
import javax.inject.Inject

class AccountInfoActivity : AppCompatActivity() {

    companion object {
        fun intent(
            context: Context
        ): Intent =
            Intent(context, AccountInfoActivity::class.java)
    }

    @Inject
    lateinit var prefsStorage: PrefsStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_info)

        textFullName.text = "${prefsStorage.getFirstName()} ${prefsStorage.getSecondName()}"
        textReservations.text = "${prefsStorage.getUserReservations()}"
        textReviews.text = "${prefsStorage.getUserReviews()}"
        textPhotos.text = "${prefsStorage.getUserPhotos()}"

        buttonSignOut.setSafeOnClickListener {
            prefsStorage.setIsAuthenticated(false)
            prefsStorage.setUserReservations(0)
            finish()
        }
    }
}
