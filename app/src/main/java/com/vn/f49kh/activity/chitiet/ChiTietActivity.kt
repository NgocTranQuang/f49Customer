package com.vn.f49kh.activity.chitiet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityChitietBinding
import com.vn.f49kh.utils.Constant
import kotlinx.android.synthetic.main.activity_chitiet.*

class ChiTietActivity : BaseF49Activity<ActivityChitietBinding, ChiTietViewModel>() {

    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, ChiTietActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_chitiet
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.chi_tiet)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imgSlide.setListImage(
            mutableListOf(
                Constant.IMAGE_URL_DEFAULT,
                Constant.IMAGE_URL_DEFAULT,
                Constant.IMAGE_URL_DEFAULT,
                Constant.IMAGE_URL_DEFAULT,
                Constant.IMAGE_URL_DEFAULT
            ), 0
        )
    }
}