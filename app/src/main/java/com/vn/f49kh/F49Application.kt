package com.vn.f49kh

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.utils.Constant
import java.util.*

class F49Application : MultiDexApplication() {
    init {
        instance = this
    }

    companion object {
        var instance: F49Application? = null

        fun applicationContext(): F49Application {
            return instance as F49Application
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Fresco.initialize(this);

//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        } else {
//            val fabric = Fabric.Builder(this)
//                .kits(Crashlytics())
//                .debuggable(BuildConfig.DEBUG) // Enables Crashlytics debugger
//                .build()
//            Fabric.with(fabric)
//
//        }
////        val fabric = Fabric.Builder(this)
////            .kits(Crashlytics())
////            .debuggable(BuildConfig.DEBUG) // Enables Crashlytics debugger
////            .build()
////        Fabric.with(fabric)
////        Fabric.with(this, Crashlytics())
//
//        Fresco.initialize(this);
    }

    fun getConfigLocale(base: Context): Context {
        val locale = Locale(PreferenceUtils.getString(base, PreferenceUtils.KEY_LANGUAGE, Constant.LOCALE_VN))
        Locale.setDefault(locale)
        val config = base.resources.configuration
        config.setLocale(locale)
        return createConfigurationContext(config)
    }

}