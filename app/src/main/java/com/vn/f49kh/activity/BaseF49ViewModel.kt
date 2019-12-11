package com.vn.f49kh.activity

import android.app.Application
import android.os.Handler
import com.vn.custom.activity.base.BaseViewModel
import com.vn.custom.util.Constant
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.extension.checkRequest
import com.vn.f49kh.model.BaseResponse
import com.vn.f49kh.service.ApiService
import com.vn.f49kh.service.ServiceRepository
import io.reactivex.Observable

open class BaseF49ViewModel(app: Application) : BaseViewModel(app) {
    //    protected val mContext = (app as F49Application).getConfigLocale(app.baseContext)
    protected val mApiService = ServiceRepository.createService(mContext, ApiService::class.java)
    protected var pageSize = PreferenceUtils.getInt(mContext, PreferenceUtils.KEY_PAGE_SIZE, Constant.PAGE_SIZE_DEFAULT)

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

    fun <T> handleRequestServiceObject(obsever: Observable<BaseResponse<T?>>, onResult: (T?) -> Unit) {
        Handler().postDelayed(Runnable {
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
        }, 100)

    }
}