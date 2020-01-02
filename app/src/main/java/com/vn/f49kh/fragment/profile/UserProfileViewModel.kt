package com.vn.f49kh.fragment.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.extension.checkRequest
import com.vn.f49kh.utils.GeneralUtils

class UserProfileViewModel(app: Application) : BaseF49ViewModel(app) {
    var isLogoutSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun changePassword(oldPassword: String, newPassword: String) {
        showLoading()
        handleRequestServiceObject(
            mApiService?.changePassword(
                PreferenceUtils.getString(
                    mContext,
                    PreferenceUtils.KEY_EMAIL,
                    ""
                ) ?: "", oldPassword, newPassword
            )
        ) {
            showDialog(mContext.getString(R.string.change_password_ss))
        }
    }
    fun pushTokenFirebase(email: String?, token: String?, deviceName: String?, flg: Boolean) {
        if (email.isNullOrBlank()) {
            showDialogError(mContext.getString(R.string.provide_account))
            return
        }
        showLoading()
        mApiService?.pushFirebaseToken(email, token, GeneralUtils.getDeviceId(mContext) ?:"", flg).checkRequest(mContext)?.subscribe({
            isLogoutSuccess.value = true
        }, {
            showDialogError(it)
            isLogoutSuccess.value = false
        }, {
            hideLoading()
            isLogoutSuccess.value = false
        })
    }
}