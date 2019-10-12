package com.vn.f49kh.utils

import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView

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
    }

}