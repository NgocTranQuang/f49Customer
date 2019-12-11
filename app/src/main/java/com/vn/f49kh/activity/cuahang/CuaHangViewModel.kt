package com.vn.f49kh.activity.cuahang

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.model.CuaHangDTO

class CuaHangViewModel(app: Application) : BaseF49ViewModel(app) {
    var listCuaHang: MutableLiveData<MutableList<CuaHangDTO>> = MutableLiveData()

    fun getDanhSachCuaHang() {
        showLoading()
        handleRequestServiceObject(mApiService?.getDanhSachCuaHang()) {
            listCuaHang.value = it
        }
    }


}