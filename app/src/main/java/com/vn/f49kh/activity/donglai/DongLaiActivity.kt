package com.vn.f49kh.activity.donglai

import android.content.Context
import android.content.Intent
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.chitiet.ChiTietActivity
import com.vn.f49kh.activity.expandf49.BaseExpandF49Activity
import com.vn.f49kh.base.Base
import com.vn.f49kh.enumApp.ExpandType
import com.vn.f49kh.model.taisan.TaiSanDTO

class DongLaiActivity : BaseExpandF49Activity() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, DongLaiActivity::class.java))

        }
    }


    override fun getTitleToolbar(): String? {
        return getString(R.string.dong_lai)
    }

    override fun observer() {
        super.observer()
        Base.typeExpand = ExpandType.DONG_LAI
        mViewModel?.getListDongLai(PreferenceUtils.getString(this, PreferenceUtils.KEY_EMAIL, ""))
    }

    override fun onclickItem(taiSanTypeDTO: TaiSanDTO) {
        ChiTietActivity.start(this, taiSanTypeDTO.id ?: 0)

    }
}