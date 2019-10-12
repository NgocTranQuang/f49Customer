package com.vn.f49kh.activity.huongdan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityHuongdanBinding
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_huongdan.*

class HuongDanActivity : BaseF49Activity<ActivityHuongdanBinding, HuongDanViewModel>() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, HuongDanActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_huongdan
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.huong_dan)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setonClick()
    }

    private fun setonClick() {
        cvCamDoOnline.setOnSingleClickListener {

            handleCollapse(lnContentCamDoOnline,tvCamdoOnline)

        }
        cvCamDoTaiCuaHang.setOnSingleClickListener {
            handleCollapse(lnContentCamDoTaiCuaHang,tvCamdoTaiCuahang)
        }
    }
    fun handleCollapse(lnContent: View, image: TextView) {
        if (lnContent.visibility == View.GONE) {
            lnContent.visibility = View.VISIBLE
            image.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_baoquan_arrow_up),
                null
            )
        } else {
            lnContent.visibility = View.GONE
            image.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_baoquan_arrow_down),
                null
            )
        }

    }
}