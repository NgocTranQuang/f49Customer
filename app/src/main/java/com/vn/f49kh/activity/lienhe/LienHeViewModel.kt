package com.vn.f49kh.activity.lienhe

import android.app.Application
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel

class LienHeViewModel(app: Application) : BaseF49ViewModel(app) {

    fun putLienHe(fullName: String?, province: String?, phone: String?, email: String?, content: String?) {
        showLoading()
        handleRequestServiceObject(mApiService?.putLienHe(fullName,province,phone,email,content)){
            showDialogThenAutoBack(mContext.getString(R.string.da_gui_yeu_cau_lien_he))
        }

    }
}