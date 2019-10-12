package com.vn.f49kh.activity

import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.vn.custom.activity.base.BaseViewModel
import com.vn.custom.util.GeneralUtil
import com.vn.f49kh.activity.login.LoginActivity
import com.xxx.baseproject.base.BaseMVVMActivity

abstract class BaseF49Activity<V : ViewDataBinding, B : BaseViewModel> : BaseMVVMActivity<V, B>() {

    fun checkLogin(todo: () -> Unit) {
        if (GeneralUtil.isLogined(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            todo.invoke()
        }
    }
}