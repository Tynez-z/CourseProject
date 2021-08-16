package com.example.firebaseauthentication.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

internal fun View.longSnackBar(message: Int, action: (Snackbar.() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackBar.apply {
        action?.let { it() }
        show()
    }
}

internal fun Snackbar.action(message: Int, action: (View) -> Unit) =
    setAction(message, action)

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}