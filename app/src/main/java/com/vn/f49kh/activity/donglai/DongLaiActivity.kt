package com.vn.f49kh.activity.donglai

import android.content.Context
import android.content.Intent
import com.vn.f49kh.R
import com.vn.f49kh.activity.expandf49.BaseExpandF49Activity
import com.vn.f49kh.model.taisan.TaiSanDTO
import com.vn.f49kh.model.taisan.TaiSanTypeDTO
import com.vn.f49kh.utils.Constant
import java.util.*

class DongLaiActivity : BaseExpandF49Activity() {
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, DongLaiActivity::class.java))

        }
    }


    override fun getTitleToolbar(): String? {
        return getString(R.string.dong_lai)
    }


    override fun getListItem(): MutableList<TaiSanTypeDTO> {
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