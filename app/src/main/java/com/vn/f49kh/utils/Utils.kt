package com.vn.f49kh.utils

import android.content.Context
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri


class Utils {
    companion object {
        fun animateArrowExpand(imageView: ImageView) {
            val rotate = RotateAnimation(360f, 180f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            imageView.startAnimation(rotate)
        }

        fun animateArrowCollapse(imageView: ImageView) {
            val rotate = RotateAnimation(180f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            imageView.startAnimation(rotate)
        }
        fun callHotLine(context: Context){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0906039049")
            context.startActivity(intent)
        }
    }

}