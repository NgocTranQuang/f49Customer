package com.vn.f49kh.activity.expandf49

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vn.f49kh.R
import com.vn.f49kh.activity.taisan.TaiSanViewModel
import com.vn.f49kh.adapter.taisan.TaiSanTypeAdapter
import com.vn.f49kh.databinding.ActivityTaiSanBinding
import com.vn.f49kh.extension.init
import com.vn.f49kh.model.taisan.TaiSanDTO
import com.vn.f49kh.model.taisan.TaiSanTypeDTO
import com.xxx.baseproject.base.BaseMVVMActivity
import kotlinx.android.synthetic.main.activity_tai_san.*


open class BaseExpandF49Activity : BaseMVVMActivity<ActivityTaiSanBinding, TaiSanViewModel>() {
    var adapter: TaiSanTypeAdapter? = null
    override fun getLayout(): Int {
        return R.layout.activity_tai_san
    }

    //    override fun getTitleToolbar(): String? {
//        return getString(R.string.tai_san)
//    }
//
    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getViewModelClass(): Class<TaiSanViewModel> {
        return TaiSanViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvTaiSan.init()
        adapter = TaiSanTypeAdapter(mutableListOf(), {
            it?.let {
                onclickViewAll(it)
            }
        }, {
            it?.let {
                onclickItem(it)
            }
        }, showArrowNext())
        rvTaiSan.adapter = adapter
        rvTaiSan.post {
            rvTaiSan.scrollToPosition(0)
        }
    }

    open fun showArrowNext(): Boolean {
        return true
    }

    override fun observer() {
        super.observer()
        mViewModel?.listTaiSan?.observe(this, Observer {
            setListData(it)
        })
    }

    open fun onclickViewAll(taiSanTypeDTO: TaiSanTypeDTO) {

    }

    open fun onclickItem(taiSanTypeDTO: TaiSanDTO) {

    }

//    override fun showLoading() {
//        shimmer.visibility = View.VISIBLE
//    }
//
//    override fun hideLoading() {
//        shimmer.visibility = View.GONE
//    }

    open fun setListData(data: MutableList<TaiSanTypeDTO>?) {
        if (data == null || data.size == 0) {
            tvNoData.visibility = View.VISIBLE
        } else {
            tvNoData.visibility = View.GONE
            adapter?.insertData(data)
            rvTaiSan.post {
                rvTaiSan.scrollToPosition(0)
            }
        }
    }

}