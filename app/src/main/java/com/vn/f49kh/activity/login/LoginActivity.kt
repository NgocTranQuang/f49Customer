package com.vn.f49kh.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.main.MainActivity
import com.vn.f49kh.databinding.ActivityLoginBinding
import com.vn.f49kh.utils.GeneralUtils
import com.xxx.baseproject.base.BaseMVVMActivity
import extension.setOnSingleClickListener
import extension.underLine
import kotlinx.android.synthetic.main.activity_lienhe.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVVMActivity<ActivityLoginBinding, LoginViewModel>() {
    var email: String? = null
    var password: String? = null
    var tokenFireBase: String = ""

    companion object {
        val KEY_DATA = "isFromSplashActivity"
        fun start(context: Context?) {
            context?.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setEventClick()
    }


    private fun initView() {
//        tvForgotPassword.underLine()

//        mViewBinding?.viewModel = mViewModel
//        var isRemember = PreferenceUtils.getBoolean(this, PreferenceUtils.KEY_REMEMBER_LOGIN, false)
//        cbRemember.isChecked = isRemember
//        if (isRemember) {
//            email = PreferenceUtils.getString(this, PreferenceUtils.KEY_EMAIL, "")
//            password = PreferenceUtils.getString(this, PreferenceUtils.KEY_PASSWORD, "")
//        }
//        mViewModel?.version?.value = "F49 version ${BuildConfig.VERSION_NAME}"

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("token", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                tokenFireBase = token ?: ""
                PreferenceUtils.writeString(this, PreferenceUtils.KEY_TOKEN_FIREBASE, token)
                Log.d("token", token)
            })
    }


    override fun observer() {
        super.observer()
        mViewModel?.isLoginSuccessfully?.observe(this, Observer {
            if (it == true) {

                if (it == true) {
                    if (!tokenFireBase.isNullOrBlank()) {
                        mViewModel?.pushTokenFirebase(et_email.text.toString(), tokenFireBase, GeneralUtils.getDeviceId(this) ?:"", true)
                    }
//                    MainActivity.start(this)
                }

//                if (!tokenFireBase.isNullOrBlank()) {
//                    mViewModel?.pushTokenFirebase(
//                        edtEmail.text.toString(),
//                        tokenFireBase,
//                        GeneralUtils.getDeviceId(this) ?: "",
//                        true
//                    )
//                }
//                PreferenceUtils.writeBoolean(this, PreferenceUtils.KEY_REMEMBER_LOGIN, cbRemember.isChecked)
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