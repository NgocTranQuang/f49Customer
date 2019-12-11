package com.vn.f49kh.activity.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.f49.model.login.LoginDTO
import com.google.gson.Gson
import com.vn.custom.util.Constant
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49ViewModel
import com.vn.f49kh.base.Base
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginViewModel(app: Application) : BaseF49ViewModel(app) {
    val GRANT_TYPE = "password"
    val TRY_AGAIN = ". Xin vui lòng thử lại."
    val ACCOUNT_INCORRECT = "Tài khoản không tồn tại"
    val PASSWORD_INCORRECT = "Sai mật khẩu"
    var isLoginSuccessfully: MutableLiveData<Boolean> = MutableLiveData()

    fun login(email: String, password: String) {
        showLoading()
        mApiService?.login(
            GRANT_TYPE, email ?: "", password
                ?: ""
        ).materialize()?.map { noti ->
            noti.isOnError.let {
                noti.error?.let {
                    if (it.message?.contains(Constant.ERROR_NETWORK) == true) {
                        throw Throwable(mContext.getString(R.string.network_not_available), it)
                    } else {
                        var message = it.message
                        if ((it as HttpException).code() == 400) {
                            var messageJson = (noti.error as HttpException).response().errorBody()?.string()
                            message = Gson().fromJson(messageJson, LoginDTO::class.java).error_description ?: ""
                            if (message.contains(ACCOUNT_INCORRECT) || message.contains(PASSWORD_INCORRECT)) {
                                message = message + TRY_AGAIN
                            }
                        }
                        throw Throwable(message, it)
                    }
                }
            }
            noti
        }?.filter {
            !it.isOnError

        }?.dematerialize<LoginDTO>()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                if (it.error == null) {
                    PreferenceUtils.writeString(
                        mContext,
                        PreferenceUtils.KEY_TOKEN,
                        it.token_type + " " + it.access_token
                    )
                    PreferenceUtils.writeString(mContext, PreferenceUtils.KEY_EMAIL, email)
                    PreferenceUtils.writeInt(mContext, PreferenceUtils.KEY_PAGE_SIZE, it.pageSize?.toInt())
                    PreferenceUtils.writeString(mContext, PreferenceUtils.KEY_PASSWORD, password)
                    PreferenceUtils.writeBoolean(mContext, PreferenceUtils.KEY_IS_LOGOUT, false)
                    Base.pageSize = it.pageSize.toInt()
                    Base.username = it
                    isLoginSuccessfully.value = true
                } else {
                    isLoginSuccessfully.value = false
                    showDialogError(it.error_description ?: "")
                }
            }, {
                isLoginSuccessfully.value = false
                showDialogError(it)
            }, {
                hideLoading()
            })
    }

}