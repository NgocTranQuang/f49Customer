package com.vn.f49kh.fragment.profile

import android.app.Application
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel

class UserProfileViewModel(app: Application) : BaseF49ViewModel(app) {
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
}