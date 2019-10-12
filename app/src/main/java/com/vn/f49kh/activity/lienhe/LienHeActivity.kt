package com.vn.f49kh.activity.lienhe

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityLienheBinding
import kotlinx.android.synthetic.main.activity_lienhe.*

class LienHeActivity : BaseF49Activity<ActivityLienheBinding, LienHeViewModel>() {

    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, LienHeActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_lienhe
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.lien_he)
    }
}