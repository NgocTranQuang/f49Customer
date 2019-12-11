package com.vn.f49kh.activity.dangkycamdo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.model.taisan.TaiSanTypeDTO

class DangKyCamDoViewModel(app: Application) : BaseF49ViewModel(app) {
    var listLoaiTaiSan: MutableLiveData<MutableList<TaiSanTypeDTO>> = MutableLiveData()
    var listDanhMuc: MutableLiveData<MutableList<String>> = MutableLiveData()
    fun getLoaiTaiSan() {
        handleRequestServiceObject(mApiService?.getLoaiTaiSan()) {
            listLoaiTaiSan.value = it
        }
    }

    fun putDKCD(balance: Double, name: String, asset: String, brand: String, phone: String, description: String) {
        showLoading()
        handleRequestServiceObject(mApiService?.putDangKyCamdo(balance, name, asset, brand, phone, description)) {
            showDialogThenAutoBack(mContext.getString(R.string.we_will_call_for_you_later))
        }
    }
    fun getDanhMucSanPham(id: String) {
        showLoading()
        handleRequestServiceObject(mApiService?.getDanhMucSanPham(id, "")) {
            listDanhMuc.value = it ?: mutableListOf()
        }
    }
}