package com.vn.f49kh.activity.chitiet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityChitietBinding
import kotlinx.android.synthetic.main.activity_chitiet.*

class ChiTietThanhLyActivity : BaseF49Activity<ActivityChitietBinding, ChiTietViewModel>() {

//    var adapter: ChiTietAdapter? = null

    companion object {
        val KEY_DATA_ID_HOP_DONG = "KEY_DATA_ID_HOP_DONG"
        fun start(context: Context?, idHangThanhLy: Int) {
            context?.startActivity(
                Intent(context, ChiTietThanhLyActivity::class.java).putExtra(
                    KEY_DATA_ID_HOP_DONG,
                    idHangThanhLy
                )
            )
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_chitiet
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.chi_tiet)
    }

    override fun observer() {
        super.observer()
//        rvData.init()
//        adapter = ChiTietAdapter(mutableListOf())
//        rvData.adapter = adapter
//        mViewModel?.chiTietThanhLy?.observe(this, Observer {
////            adapter?.insertData(it ?: mutableListOf())
//        })
        mViewModel?.price?.observe(this, Observer {
            tvPrice.text = it ?: ""
        })
        mViewModel?.listImage?.observe(this, Observer {
            imgSlide.setListImage(it, 0)
        })
        mViewModel?.nameProduct?.observe(this, Observer {
            tvName.text = it
        })
        mViewModel?.noidung?.observe(this, Observer {
            tvNoiDung.text = it ?: getString(R.string.no_des)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var idHangThanhLy = intent?.getIntExtra(KEY_DATA_ID_HOP_DONG, 0)
        mViewModel?.getChiTietThanhLy(idHangThanhLy)
    }
}