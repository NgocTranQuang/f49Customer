package com.vn.f49kh.activity.main

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.app.f49.firebase.MyFirebaseMessagingService
import com.app.f49.model.notification.NotificationVO
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vn.custom.activity.base.BaseActivity
import com.vn.custom.util.GeneralUtil
import com.vn.f49kh.R
import com.vn.f49kh.activity.login.LoginActivity
import com.vn.f49kh.extension.handleScreenId
import com.vn.f49kh.fragment.Home.HomeFragment
import com.vn.f49kh.fragment.dashboard.other.OtherFragment
import com.vn.f49kh.fragment.dashboard.service.ServiceFragment
import com.vn.f49kh.fragment.profile.ProfileUserFragment
import com.xxx.baseproject.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.tab_home -> {
                displayFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_service -> {
                displayFragment(ServiceFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_qrcode -> {

                return@OnNavigationItemSelectedListener false
            }

            R.id.tab_other -> {
                displayFragment(OtherFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.tab_me -> {
                if (GeneralUtil.isLogined(this)) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener false

                } else {
                    displayFragment(ProfileUserFragment())
                    return@OnNavigationItemSelectedListener true
                }

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()
        handleIntent(intent)
        displayFragment(HomeFragment())

    }

    private fun initTab() {

        nav_view?.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        nav_view?.showBadgeQRCode(1, 1)

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradiant(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            val background = activity.resources.getDrawable(R.drawable.bg_toolbar)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = activity.resources.getColor(android.R.color.transparent)
//            window.navigationBarColor = activity.resources.getColor(android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }
    }



    private fun displayFragment(frag: BaseFragment) {
        try {
            val transaction = supportFragmentManager.beginTransaction()

            //hide other
            supportFragmentManager.fragments.forEach {
                if (it != frag) {
                    transaction.hide(it)
                }
            }

            if (!frag.isAdded) {
                transaction.add(R.id.lnContent, frag)
            } else {
                transaction.show(frag)
            }

            transaction.commit()

        } catch (e: Exception) {

        }
    }

    fun addFragment(fragment: Fragment?, tag: String) {
        if (fragment == null) {
            return
        }
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        for (fragmentInStack in fragmentManager.fragments) {
            transaction.hide(fragmentInStack)
        }
        if (fragmentManager.findFragmentByTag(tag) != null) {
            getFragmentManager().popBackStack(tag, 0)
            transaction.addToBackStack(tag)
            transaction.show(fragment).commit()
        } else {
            transaction.add(R.id.lnContent, fragment, tag)
            transaction.addToBackStack(tag)
            transaction.commitAllowingStateLoss()
        }
    }

    fun selectTab(i: Int) {
        nav_view.selectedItemId = R.id.tab_home
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent == null) {
            log("intent null")
        } else {
            log("intent # null")
        }
        handleIntent(intent)
    }
    fun log(msg: String) {
        Log.d("MainActivityNewIntent", msg)
    }
    fun handleIntent(intent: Intent?) {
        var idItem = intent?.getStringExtra(MyFirebaseMessagingService.ITEMID)
        var idScreen = intent?.getStringExtra(MyFirebaseMessagingService.SCREENID)
        if (idItem == null) {
            if (intent?.extras != null) {
                var notification = getDataFromBackgroundNotification(intent.extras)
                idItem = notification.itemId
                idScreen = notification.screenId
            }
        }
        this.handleScreenId(idScreen, idItem)
    }

    private fun getDataFromBackgroundNotification(bundle: Bundle): NotificationVO {
        val notitication = NotificationVO()
        for (key in bundle.keySet()) {
            val value = bundle.get(key)
            log(MyFirebaseMessagingService.TAG + "Key: " + key + " Value: " + value)
            if (TextUtils.equals(key, MyFirebaseMessagingService.ITEMID)) {
                notitication.itemId = bundle.get(key)?.toString()
            }
            if (TextUtils.equals(key, MyFirebaseMessagingService.SCREENID)) {
                notitication.screenId = bundle.get(key)?.toString()
            }

            if (TextUtils.equals(key, MyFirebaseMessagingService.TITLE)) {
                notitication.title = bundle.get(key)?.toString()
            }
//            if (TextUtils.equals(key, EOfficeFirebaseMessagingService.KEY_SITE_URL)) {
//                action.setSiteUrl(bundle.get(key)!!.toString())
//            }
//            if (TextUtils.equals(key, EOfficeFirebaseMessagingService.KEY_ITEM_ID)) {
//                val itemIdStr = bundle.get(key)!!.toString()
//                action.setItemId(if (TextUtils.isEmpty(itemIdStr)) 0 else Integer.parseInt(itemIdStr))
//            }
//            if (TextUtils.equals(key, EOfficeFirebaseMessagingService.KEY_COMPANY_ID)) {
//                action.setCompanyId(bundle.get(key)!!.toString())
//            }
        }
        return notitication
    }
}
