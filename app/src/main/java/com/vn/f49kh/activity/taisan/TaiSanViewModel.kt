package com.vn.f49kh.activity.taisan

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vn.f49kh.activity.expandf49.BaseExpandViewModel
import com.vn.f49kh.model.taisan.TaiSanTypeDTO

class TaiSanViewModel(app: Application) : BaseExpandViewModel(app) {
    var listTaiSan: MutableLiveData<MutableList<TaiSanTypeDTO>?> = MutableLiveData()
    fun getListTaiSan(idKhachHang: Int?) {
        showLoading()
        handleRequestServiceObject(mApiService?.getDanhSachTaiSan(idKhachHang)) {
            listTaiSan.value = it
        }
    }

    fun getListDongLai(soCMND: String?) {
        showLoading()
        handleRequestServiceObject(mApiService?.getDanhSachDongLai(soCMND)) {
            listTaiSan.value = it
        }
    }

    fun getListThanhLy() {
        showLoading()
        handleRequestServiceObject(mApiService?.getDanhSachHangThanhLy()) {
            listTaiSan.value = it
        }
    }
}