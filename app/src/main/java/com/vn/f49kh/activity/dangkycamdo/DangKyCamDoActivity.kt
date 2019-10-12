package com.vn.f49kh.activity.dangkycamdo

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.databinding.ActivityDangkyCamdoBinding
import com.xxx.baseproject.base.BaseMVVMActivity
import kotlinx.android.synthetic.main.activity_dangky_camdo.*

class DangKyCamDoActivity : BaseMVVMActivity<ActivityDangkyCamdoBinding, DangKyCamDoViewModel>() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, DangKyCamDoActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_dangky_camdo
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.dang_ky_cam_do)
    }
}