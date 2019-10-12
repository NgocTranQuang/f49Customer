package com.vn.f49kh.activity

import android.app.Application
import com.vn.custom.activity.base.BaseViewModel
import com.vn.custom.util.Constant
import com.vn.f49kh.extension.checkRequest
import com.vn.f49kh.model.BaseResponse
import com.vn.f49kh.service.ApiService
import com.vn.f49kh.service.ServiceRepository
import io.reactivex.Observable

open class BaseF49ViewModel(app: Application) : BaseViewModel(app) {
    protected val mApiService = ServiceRepository.createService(mContext, ApiService::class.java)

    fun <T> handleRequestService(
        obsever: Observable<BaseResponse<MutableList<T>>>,
        onResult: (MutableList<T>?) -> Unit
    ) {
        obsever.checkRequest(mContext)?.subscribe({
            onResult.invoke(it)
        }, {
            if (it.message?.contains(Constant.KEY_ITEM_NULL) == true) {
                onResult.invoke(mutableListOf())
                hideLoading()
            } else {
                showDialogError(it)
            }
        }, {
            hideLoading()
        })
    }

    fun <T> handleRequestServiceObject(obsever: Observable<BaseResponse<T>>, onResult: (T?) -> Unit) {
        obsever.checkRequest(mContext)?.subscribe({
            onResult.invoke(it)
        }, {
            if (it.message?.contains(Constant.KEY_ITEM_NULL) == true) {
                onResult.invoke(null)
                hideLoading()
            } else {
                showDialogError(it)
            }
        }, {
            hideLoading()
        })
    }
}