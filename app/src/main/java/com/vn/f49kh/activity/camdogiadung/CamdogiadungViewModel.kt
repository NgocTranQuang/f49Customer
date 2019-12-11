package com.vn.f49kh.activity.camdogiadung

import android.app.Application
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel

class CamdogiadungViewModel(app: Application) : BaseF49ViewModel(app) {

    fun putCamDoGiaDung(fullname: String, phone: String, info: String) {
        showLoading()
        handleRequestServiceObject(mApiService?.putCamDoGiaDung(fullname, phone, info)) {
            showDialogThenAutoBack(mContext.getString(R.string.we_will_call_for_you_later))
        }
    }

}