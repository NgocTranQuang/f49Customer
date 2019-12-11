package com.vn.f49kh.activity.dinhgiataisan

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.extension.checkRequest
import com.vn.f49kh.model.dinhgiataisan.UploadImageDTO

class DinhGiaTaiSanViewModel(app: Application) : BaseF49ViewModel(app) {

    var currentIndex: MutableLiveData<Int> = MutableLiveData()
    var listImageUpload: MutableList<UploadImageDTO> = mutableListOf()
    var finishedUploading: MutableLiveData<Boolean> = MutableLiveData()
    var listDanhMuc: MutableLiveData<MutableList<String>> = MutableLiveData()
    var linkImage = ""

    init {
        currentIndex.value = -1
    }

    fun uploadImage() {
        if (listImageUpload.size < 1) {
            showDialogError(mContext.getString(R.string.chon_anh))
            return
        }
        if (listImageUpload.size > (currentIndex.value ?: 0) + 1) {
            currentIndex.value = currentIndex.value!! + 1
            uploadOneImage(listImageUpload.get(currentIndex.value!!))
        } else {
            finishedUploading.value = true
        }
    }

    fun uploadOneImage(image: UploadImageDTO) {
        mApiService?.uploadImage(image)?.checkRequest(mContext)?.subscribe({
            linkImage = it ?: ""
            uploadImage()
        }, {
            currentIndex.value = -1
            finishedUploading.value = false
        }, {

        })

    }

    fun guiDinhGiaTaiSan(infoProduct: String, fullName: String, phone: String,imgPath : String) {
        handleRequestServiceObject(mApiService?.putDinhGiaTaiSan(infoProduct, fullName, phone,imgPath)) {
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