package com.vn.f49kh.activity.camdogiadung

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.databinding.ActivityCamdoGiadungBinding
import com.vn.f49kh.extension.isPhoneNumber
import com.xxx.baseproject.base.BaseMVVMActivity
import extension.setOnSingleClickListener
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

    override fun observer() {
        super.observer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onclick()
    }

    private fun onclick() {
        lnBottom.setOnSingleClickListener {

            if (edtFullName.text.toString().isNullOrEmpty() || !edtPhoneNumber.isPhoneNumber() || edtTenTaiSan.text.toString().isNullOrEmpty()) {
                showDialog(getString(R.string.invalid_data))
                return@setOnSingleClickListener
            }
            mViewModel?.putCamDoGiaDung(
                edtFullName.text.toString(),
                edtPhoneNumber.text.toString(),
                edtTenTaiSan.text.toString()
            )
        }
    }
}