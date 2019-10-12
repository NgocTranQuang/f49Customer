package com.vn.f49kh.activity.expandf49

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.vn.f49kh.R
import com.vn.f49kh.activity.taisan.TaiSanViewModel
import com.vn.f49kh.adapter.taisan.TaiSanTypeAdapter
import com.vn.f49kh.databinding.ActivityTaiSanBinding
import com.vn.f49kh.extension.init
import com.vn.f49kh.model.taisan.TaiSanDTO
import com.vn.f49kh.model.taisan.TaiSanTypeDTO
import com.vn.f49kh.utils.Constant
import com.xxx.baseproject.base.BaseMVVMActivity
import kotlinx.android.synthetic.main.activity_tai_san.*
import java.util.*


open class BaseExpandF49Activity : BaseMVVMActivity<ActivityTaiSanBinding, TaiSanViewModel>() {

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
        rvTaiSan.adapter = TaiSanTypeAdapter(getListItem(), {
            it?.let {
                onclickViewAll(it)
            }
        }) {
            it?.let {
                onclickItem(it)
            }
        }
        ViewCompat.setNestedScrollingEnabled(rvTaiSan, false);
        rvTaiSan.setNestedScrollingEnabled(false);
        rvTaiSan.post {
            rvTaiSan.scrollToPosition(0)
        }
    }

    open fun onclickViewAll(taiSanTypeDTO: TaiSanTypeDTO) {

    }

    open fun onclickItem(taiSanTypeDTO: TaiSanDTO) {

    }

    open fun getListItem(): MutableList<TaiSanTypeDTO> {
        var list = mutableListOf<TaiSanTypeDTO>()
        list.add(TaiSanTypeDTO().apply {
            this.name = "Lap top"
            var taiSan = TaiSanDTO().apply {
                this.name = "lap top doi moi gia re"
                this.imageURL = Constant.IMAGE_URL_DEFAULT
                this.ngayDenHan = Date()
                this.noLaiDenHan = 2000000
                this.price = 12000000
            }
            var lisTS = mutableListOf<TaiSanDTO>()
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            this.listTaiSan = lisTS
        })
        list.add(TaiSanTypeDTO().apply {
            this.name = "Lap top"
            var taiSan = TaiSanDTO().apply {
                this.name = "lap top doi moi gia re"
                this.imageURL = Constant.IMAGE_URL_DEFAULT
                this.ngayDenHan = Date()
                this.noLaiDenHan = 2000000
                this.price = 12000000
            }
            var lisTS = mutableListOf<TaiSanDTO>()
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            this.listTaiSan = lisTS
        })
        list.add(TaiSanTypeDTO().apply {
            this.name = "Lap top"
            var taiSan = TaiSanDTO().apply {
                this.name = "lap top doi moi gia re"
                this.imageURL = Constant.IMAGE_URL_DEFAULT
                this.ngayDenHan = Date()
                this.noLaiDenHan = 2000000
                this.price = 12000000
            }
            var lisTS = mutableListOf<TaiSanDTO>()
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            this.listTaiSan = lisTS
        })
        list.add(TaiSanTypeDTO().apply {
            this.name = "Lap top"
            var taiSan = TaiSanDTO().apply {
                this.name = "lap top doi moi gia re"
                this.ngayDenHan = Date()
                this.imageURL = Constant.IMAGE_URL_DEFAULT
                this.noLaiDenHan = 2000000
                this.price = 12000000
            }
            var lisTS = mutableListOf<TaiSanDTO>()
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            lisTS.add(taiSan)
            this.listTaiSan = lisTS
        })
        return list
    }
}