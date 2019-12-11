package com.vn.f49kh.activity.chitiet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityChitietDonglaiBinding
import com.vn.f49kh.utils.Utils
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_chitiet_donglai.*

class ChiTietActivity : BaseF49Activity<ActivityChitietDonglaiBinding, ChiTietViewModel>() {

    companion object {
        val KEY_DATA_ID_HOP_DONG = "KEY_DATA_ID_HOP_DONG"
        fun start(context: Context?, idHopDong: Int) {
            context?.startActivity(
                Intent(context, ChiTietActivity::class.java).putExtra(
                    KEY_DATA_ID_HOP_DONG,
                    idHopDong
                )
            )
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_chitiet_donglai
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.chi_tiet)
    }

    override fun observer() {
        super.observer()
        mViewModel?.chiTietDongLai?.observe(this, Observer {
            imgSlide.setListImage(it?.listImage?.map { it.url?:"" }?.toMutableList() ?: mutableListOf<String>(), 0)

            mViewBinding?.data = it
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var idHopDong = intent?.getIntExtra(KEY_DATA_ID_HOP_DONG, 0)
        mViewModel?.getChiTietDongLai(idHopDong)
        initView()
    }

    private fun initView() {
        btnCallHotLine.setOnSingleClickListener {
            Utils.callHotLine(this)
        }
    }
}