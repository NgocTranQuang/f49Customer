package com.vn.f49kh.activity.main

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.vn.custom.activity.base.BaseActivity
import com.vn.custom.util.GeneralUtil
import com.vn.f49kh.R
import com.vn.f49kh.activity.login.LoginActivity
import com.vn.f49kh.databinding.TabMainViewBinding
import com.vn.f49kh.databinding.TabQrCodeBinding
import com.vn.f49kh.fragment.Home.HomeFragment
import com.vn.f49kh.fragment.dashboard.other.OtherFragment
import com.vn.f49kh.fragment.dashboard.service.ServiceFragment
import com.vn.f49kh.fragment.profile.ProfileUserFragment
import com.xxx.baseproject.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), TabLayout.OnTabSelectedListener {


    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private val TAB_HOME_POSITION = 0
    private val TAB_SERVICE_POSITION = 1
    private val TAB_SCAN_QRCODE_POSITION = 2
    private val TAB_OTHER_POSITION = 3
    private val TAB_USER_POSITION = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()
        displayFragment(HomeFragment())
//        setStatusBarGradiant(this)
    }

    private fun initTab() {
        tab_layout.addTab(
            tab_layout.newTab()
                .setCustomView(getTabMain(R.drawable.tab_home_selected, R.string.title_home)), TAB_HOME_POSITION
        )
        tab_layout.addTab(
            tab_layout.newTab()
                .setCustomView(getTabMain(R.drawable.tab_service_selected, R.string.service)), TAB_SERVICE_POSITION
        )
        tab_layout.addTab(
            tab_layout.newTab()
                .setCustomView(getTabSwitchSiteView()), TAB_SCAN_QRCODE_POSITION
        )
        tab_layout.addTab(
            tab_layout.newTab()
                .setCustomView(getTabMain(R.drawable.tab_other_selected, R.string.other)),
            TAB_OTHER_POSITION
        )
        tab_layout.addTab(
            tab_layout.newTab()
                .setCustomView(getTabMain(R.drawable.tab_me_selected, R.string.toi)), TAB_USER_POSITION
        )
        tab_layout.addOnTabSelectedListener(this)
    }

    private fun getTabSwitchSiteView(): View {

        var mTabSwitchSiteViewBinding = DataBindingUtil.inflate<TabQrCodeBinding>(
            layoutInflater, R.layout.tab_qr_code,
            tab_layout, false
        )
        mTabSwitchSiteViewBinding.imgBgWorkSite.setOnClickListener({ v ->
            //            if (mWorkSiteList != null && mWorkSiteList.size > 0) {
//                mCompanyListDialog.show()
//            }
        })

        return mTabSwitchSiteViewBinding.getRoot()
    }

    private fun getTabMain(iconId: Int, titleStrId: Int): View {
        var mTabMainViewBinding = DataBindingUtil.inflate<TabMainViewBinding>(
            layoutInflater, R.layout.tab_main_view!!,
            tab_layout!!, false
        )
        mTabMainViewBinding.imgIcon.setImageDrawable(getDrawable(iconId))
        mTabMainViewBinding.tvTitle.setText(titleStrId)
        mTabMainViewBinding.tvTitle.setAllCaps(true)
        return mTabMainViewBinding.getRoot()
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        when (p0?.position) {
            TAB_HOME_POSITION -> {
                displayFragment(HomeFragment())
            }
            TAB_SERVICE_POSITION -> {
                displayFragment(ServiceFragment())
            }
            TAB_SCAN_QRCODE_POSITION -> {

            }
            TAB_OTHER_POSITION -> {
                displayFragment(OtherFragment())
            }
            TAB_USER_POSITION -> {
                if (GeneralUtil.isLogined(this)) {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    displayFragment(ProfileUserFragment())
                }
            }
        }
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

    fun checkLogin(todo: () -> Unit) {
        if (GeneralUtil.isLogined(this)) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            todo.invoke()
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

}
