package com.vn.f49kh.activity.baoquan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityBaoquanBinding
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_baoquan.*

class BaoQuanActivity : BaseF49Activity<ActivityBaoquanBinding, BaoQuanViewModel>() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, BaoQuanActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_baoquan
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.bao_quan)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onClick()
        mViewBinding?.data = mViewModel
    }

    private fun onClick() {
        cvTaiSanNho.setOnSingleClickListener {

            handleCollapse(lnContentTaiSanNho, tvTaiSanNho)

        }
        cvTaiSanLon.setOnSingleClickListener {

            handleCollapse(lnContentTaiSanLon, tvTaiSanLon)
        }
        cvHeThongCamera.setOnSingleClickListener {
            handleCollapse(lnContentHeThongCamera, tvHeThong)
        }


    }

    fun handleCollapse(lnContent: View, image: TextView) {
        if (lnContent.visibility == View.GONE) {
            lnContent.visibility = View.VISIBLE
            image.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_baoquan_arrow_up),
                null
            )
        } else {
            lnContent.visibility = View.GONE
            image.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_baoquan_arrow_down),
                null
            )
        }

    }

}