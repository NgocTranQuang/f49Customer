package com.vn.f49kh.model.taisan

import com.vn.f49kh.extension.toPrice
import com.vn.f49kh.extension.toShow
import java.util.*

class TaiSanDTO {
    var id: String? = null
    var imageURL: String? = null
    var name: String? = null
    var ngayDenHan: Date? = null
    var noLaiDenHan: Long? = null
    var content: String? = null
    var dongGiua: String? = null
        get() {
            if (ngayDenHan != null) {
                var time = "Ngay den han :" + ngayDenHan?.toShow()
                if (noLaiDenHan != null) {
                    time = time + "\n" + "No lai den han :" + noLaiDenHan?.toPrice()
                }
                return time;

            }
            return content
        }
    var price: Long? = null
}