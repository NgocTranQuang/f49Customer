package com.vn.f49kh.activity.thanhly

import android.content.Context
import android.content.Intent
import com.vn.f49kh.R
import com.vn.f49kh.activity.chitiet.ChiTietThanhLyActivity
import com.vn.f49kh.activity.expandf49.BaseExpandF49Activity
import com.vn.f49kh.model.taisan.TaiSanDTO

class ThanhLyActivity : BaseExpandF49Activity() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, ThanhLyActivity::class.java))

        }
    }


    override fun getTitleToolbar(): String? {
        return getString(R.string.thanh_ly)
    }


    override fun onclickItem(taiSanTypeDTO: TaiSanDTO) {
        ChiTietThanhLyActivity.start(this, taiSanTypeDTO.id ?: 0)
    }

    override fun observer() {
        super.observer()
        mViewModel?.getListThanhLy()
    }

}