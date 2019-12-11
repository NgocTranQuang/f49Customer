package com.vn.f49kh.activity.cuahang

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vn.f49kh.R
import com.vn.f49kh.adapter.cuahang.CuahangAdapter
import com.vn.f49kh.databinding.ActivityCuahangBinding
import com.vn.f49kh.extension.init
import com.vn.f49kh.model.CuaHangDTO
import com.xxx.baseproject.base.BaseMVVMActivity
import kotlinx.android.synthetic.main.activity_cuahang.*

class CuaHangActivity : BaseMVVMActivity<ActivityCuahangBinding, CuaHangViewModel>() {
    var adapter: CuahangAdapter? = null

    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, CuaHangActivity::class.java))
        }
    }


    override fun getLayout(): Int {
        return R.layout.activity_cuahang
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.cua_hang)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRV()
    }

    private fun initRV() {
        rvCuaHang.init()
        adapter = CuahangAdapter(mutableListOf()) {

        }
        rvCuaHang.adapter = adapter
    }

    override fun observer() {
        super.observer()
        mViewModel?.getDanhSachCuaHang()
        mViewModel?.listCuaHang?.observe(this, Observer {
            adapter?.insertData(it)
        })
    }
}