package com.mlhysrszn.analyticahousetestcase.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("nullableText")
fun TextView.nullableText(value: Int?) {
    if (value == null) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        text = value.toString()
    }
}

@BindingAdapter("positionText")
fun TextView.positionText(value: String?) {
    text = if (value.isNullOrEmpty()) {
        "Position: -"
    } else {
        "Position: $value"
    }
}