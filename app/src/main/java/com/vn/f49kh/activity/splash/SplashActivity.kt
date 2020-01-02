package com.app.f49.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vn.custom.activity.base.BaseActivity
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.login.LoginViewModel
import com.vn.f49kh.activity.main.MainActivity

class SplashActivity : BaseActivity() {
    private val DELAY_TIME: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            //            if (!PreferenceUtils.getBoolean(this, PreferenceUtils.KEY_ALREADY_INSTALL_APP, false)) {
//                PreferenceUtils.writeBoolean(this, PreferenceUtils.KEY_ALREADY_INSTALL_APP, true)
//                startLoginActivity()
//                return@postDelayed
//            }
//            if (!PreferenceUtils.getBoolean(this, PreferenceUtils.KEY_IS_LOGOUT, true)) {
////                if (PreferenceUtils.getBoolean(this, PreferenceUtils.KEY_REMEMBER_LOGIN, false)) {
//                var viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
//                var email = PreferenceUtils.getString(this, PreferenceUtils.KEY_EMAIL, "") ?: ""
//                var password = PreferenceUtils.getString(this, PreferenceUtils.KEY_PASSWORD, "") ?: ""
//                viewModel.login(email, password)
//                viewModel?.isLoginSuccessfully?.observe(this, Observer {
//                    if (it == true) {
//                        startActivity(Intent(this, MainActivity::class.java))
//                    } else {
//                        PreferenceUtils.writeBoolean(
//                            this, PreferenceUtils.KEY_IS_LOGOUT, true
//                        )
//                        startActivity(Intent(this, MainActivity::class.java))
//                    }
//                })
////                } else {
////                    startActivity(Intent(this, MainActivity::class.java))
////                }
//            } else {
                startActivity(Intent(this, MainActivity::class.java))
//            }
        }, DELAY_TIME)
    }

}