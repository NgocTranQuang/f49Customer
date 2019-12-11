package com.vn.f49kh.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vn.f49kh.R
import com.vn.f49kh.extension.toPrice
import com.vn.f49kh.extension.toShow
import java.util.*

@BindingAdapter("android:imageUrl")
fun ImageView.binImageUrl(url: String?) {
    if (url == null) {
        setImageResource(R.drawable.ic_image_error)
    } else {
        val options = RequestOptions()
            .error(R.drawable.ic_image_error)
        Glide.with(this).load(url).apply(options).into(this)
    }
}

@BindingAdapter("android:imageInt")
fun ImageView.binImageInt(drawable: Int?) {
    drawable?.let {
        setImageResource(drawable)
    }
}

@BindingAdapter("android:date")
fun TextView.bindDate(date: Date?) {
    if (date != null) {
        text = date.toShow()
    } else {
        text = ""
    }

}

@BindingAdapter("android:money")
fun TextView.bindMoney(money: Double?) {
    if (money != null) {
        text = money.toPrice()
    } else {
        text = "0"
    }

}

@BindingAdapter("android:money")
fun TextView.bindMoney(money: Long?) {
    if (money != null) {
        text = money.toPrice()
    } else {
        text = "0"
    }

}