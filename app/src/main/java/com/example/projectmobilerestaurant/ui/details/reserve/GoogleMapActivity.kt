package com.example.projectmobilerestaurant.ui.details.reserve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.entities.Restaurant

class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        fun intent(
            context: Context,
            restaurant: Restaurant
        ): Intent = Intent(context, GoogleMapActivity::class.java).apply {
            putExtra("1", restaurant)
        }
    }

    private lateinit var mMap: GoogleMap

    private var restaurant: Restaurant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)
        intent?.apply {
            restaurant = getParcelableExtra("1")
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.googleMap) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            mMap = it
            val place = LatLng(restaurant?.latitude!!, restaurant?.longitude!!)
            mMap.addMarker(MarkerOptions().position(place).title(restaurant?.name))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 14f))
        }

    }
}
