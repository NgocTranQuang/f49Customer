package com.vn.f49kh.extension

import android.content.Context
import com.vn.f49kh.activity.chitiet.ChiTietActivity
import com.vn.f49kh.activity.chitiet.ChiTietThanhLyActivity
import com.vn.f49kh.activity.donglai.DongLaiActivity
import com.vn.f49kh.activity.thanhly.ThanhLyActivity
import com.vn.f49kh.enumApp.ScreenIDEnum

fun Context.handleScreenId(screenId: String?, idItem: String?) {
    screenId?.let {
        when (screenId) {

            ScreenIDEnum.DONG_LAI.value -> {
                DongLaiActivity.start(this)
            }
            ScreenIDEnum.CHI_TIET_DONG_LAI.value -> {
                ChiTietActivity.start(this, idItem?.toInt() ?: 0)

            }
            ScreenIDEnum.THANH_LY.value -> {
                ThanhLyActivity.start(this)
            }
            ScreenIDEnum.THANH_LY_CHI_TIET.value -> {
                ChiTietThanhLyActivity.start(this, idItem?.toInt() ?: 0)
            }
            else -> {

            }
        }
    }
}