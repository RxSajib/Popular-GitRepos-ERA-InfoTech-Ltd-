package com.git.populargitrepos.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.git.populargitrepos.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.placholder_image)
        error(R.drawable.error_load_image)
    }
}