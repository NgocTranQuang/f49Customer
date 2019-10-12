package com.vn.f49kh.activity.camdogiadung

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.databinding.ActivityCamdoGiadungBinding
import com.xxx.baseproject.base.BaseMVVMActivity
import kotlinx.android.synthetic.main.activity_camdo_giadung.*

class CamdogiadungActivity : BaseMVVMActivity<ActivityCamdoGiadungBinding, CamdogiadungViewModel>() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, CamdogiadungActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_camdo_giadung
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.cam_do_gia_dung)
    }
}