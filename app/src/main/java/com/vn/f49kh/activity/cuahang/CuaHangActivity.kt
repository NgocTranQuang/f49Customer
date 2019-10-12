package com.vn.f49kh.activity.cuahang

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
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
        adapter = CuahangAdapter(getlistCuahang()) {

        }
        rvCuaHang.adapter = adapter
    }

    fun getlistCuahang(): MutableList<CuaHangDTO> {
        var list = mutableListOf<CuaHangDTO>()
        list.add(CuaHangDTO().apply {
            imageURL = "http://f49.vn/media/Images/index/img/img-gioithieu.jpg"
            name = "Cua hang f49"
            openTime = " 6:00 AM - 18:00 PM"
            phoneNUmber = "0978736152"
            address = "1A NGuyen Duc Thuan, Tan Binh, tphcm"
        })
        list.addAll(list)
        list.addAll(list)
        list.addAll(list)
        return list
    }
}