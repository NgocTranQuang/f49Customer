package com.vn.f49kh.activity.baoquan

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vn.custom.activity.base.BaseViewModel

class BaoQuanViewModel(app: Application) : BaseViewModel(app) {

    var image1: MutableLiveData<String> = MutableLiveData()
    var image2: MutableLiveData<String> = MutableLiveData()
    var image3: MutableLiveData<String> = MutableLiveData()
    var image4: MutableLiveData<String> = MutableLiveData()
    var image5: MutableLiveData<String> = MutableLiveData()
    var image6: MutableLiveData<String> = MutableLiveData()

    init {
        image1.value = "http://f49.vn/media/images/baoquan/camera1.jpg"
        image2.value = "http://f49.vn/media/images/baoquan/camera2.jpg"
        image3.value = "http://f49.vn/media/images/baoquan/camera3.jpg"
        image4.value = "http://f49.vn/media/images/baoquan/camera4.jpg"
        image5.value = "http://f49.vn/media/images/baoquan/camera5.jpg"
        image6.value = "http://f49.vn/media/images/baoquan/camera6.jpg"
    }
}