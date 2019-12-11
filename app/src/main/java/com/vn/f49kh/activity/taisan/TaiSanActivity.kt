package com.vn.f49kh.activity.taisan

import android.content.Context
import android.content.Intent
import com.vn.f49kh.R
import com.vn.f49kh.activity.expandf49.BaseExpandF49Activity
import com.vn.f49kh.base.Base
import androidx.lifecycle.Observer
import com.vn.f49kh.enumApp.ExpandType

class TaiSanActivity : BaseExpandF49Activity() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, TaiSanActivity::class.java))

        }
    }


    override fun getTitleToolbar(): String? {
        return getString(R.string.tai_san)
    }

    override fun observer() {
        super.observer()
        Base.typeExpand = ExpandType.TAI_SAN
        mViewModel?.getListTaiSan(Base.username?.idKhachHang?.toIntOrNull())
    }

    override fun showArrowNext(): Boolean {
        return false
    }
}