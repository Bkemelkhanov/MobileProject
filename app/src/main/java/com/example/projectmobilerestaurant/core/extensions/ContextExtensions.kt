package com.example.projectmobilerestaurant.core.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.projectmobilerestaurant.R

fun Context.makeDialerIntent(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:${phone}")
    startActivity(intent)
}

fun Context.getDefaultRecyclerDivider(): DividerItemDecoration =
    DividerItemDecoration(
        this,
        DividerItemDecoration.VERTICAL
    )

fun Context.showToast(message: String?, long: Boolean = false) {
    val duration = when (long) {
        true -> Toast.LENGTH_LONG
        else -> Toast.LENGTH_SHORT
    }
    Toast.makeText(
        this,
        message ?: getString(R.string.error_generic_error),
        duration
    ).show()
}