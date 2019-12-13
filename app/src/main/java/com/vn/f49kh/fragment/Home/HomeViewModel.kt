package com.vn.f49kh.fragment.Home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.model.image.ImageDTO

class HomeViewModel(app: Application) : BaseF49ViewModel(app) {
    var listImage: MutableLiveData<MutableList<ImageDTO>> = MutableLiveData()
    fun getBannerImage() {
        showLoading()
        handleRequestServiceObject(mApiService.getBannerImage()) {
            listImage?.value = it
        }
    }

}