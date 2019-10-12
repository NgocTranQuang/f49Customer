package com.vn.f49kh.activity.login

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.main.MainActivity
import com.vn.f49kh.databinding.ActivityLoginBinding
import com.xxx.baseproject.base.BaseMVVMActivity
import extension.setOnSingleClickListener
import extension.underLine
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVVMActivity<ActivityLoginBinding, LoginViewModel>() {
    var email: String? = null
    var password: String? = null
    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cvLogin.setOnClickListener {
            MainActivity.start(this)

        }
        initView()
        setEventClick()
    }


    private fun initView() {
        tvForgotPassword.underLine()

//        mViewBinding?.viewModel = mViewModel
        var isRemember = PreferenceUtils.getBoolean(this, PreferenceUtils.KEY_REMEMBER_LOGIN, false)
        cbRemember.isChecked = isRemember
        if (isRemember) {
            email = PreferenceUtils.getString(this, PreferenceUtils.KEY_EMAIL, "")
            password = PreferenceUtils.getString(this, PreferenceUtils.KEY_PASSWORD, "")
        }
//        mViewModel?.version?.value = "F49 version ${BuildConfig.VERSION_NAME}"
    }


    override fun observer() {
        mViewModel?.isLoginSuccessfully?.observe(this, Observer {
            if (it == true) {
//                if (!tokenFireBase.isNullOrBlank()) {
//                    mViewModel?.pushTokenFirebase(
//                        edtEmail.text.toString(),
//                        tokenFireBase,
//                        GeneralUtils.getDeviceId(this) ?: "",
//                        true
//                    )
//                }
                PreferenceUtils.writeBoolean(this, PreferenceUtils.KEY_REMEMBER_LOGIN, cbRemember.isChecked)
//                MainActivity.start(this)
                finish()
            }
        })
    }


    private fun setEventClick() {
        cvLogin.setOnSingleClickListener {
            if (et_email.text.isNullOrBlank() || et_password.text.isNullOrBlank()) {
                showDialog(getString(R.string.provide_account))
                return@setOnSingleClickListener
            }
            mViewModel?.login(et_email.text.toString(), et_password.text.toString())
        }
    }
}