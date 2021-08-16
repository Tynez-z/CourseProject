package com.example.firebaseauthentication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(POSTER)
fun loadFromUrl(view: ImageView, url: String?) {
    Glide.with(view)
        .load(URL_IMAGE + url)
        .centerCrop()
        .into(view)
}