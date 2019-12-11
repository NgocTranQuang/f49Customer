package com.vn.f49kh.model.taisan

import com.google.gson.annotations.SerializedName
import com.vn.f49kh.base.Base
import com.vn.f49kh.extension.toPrice
import com.vn.f49kh.extension.toShow
import java.util.*

class TaiSanDTO {

    @SerializedName(value = "id", alternate = ["idHangThanhLy"])
    var id: Int? = null
    @SerializedName(value = "idHopDong", alternate = ["soHD"])
    var idHopDong: String? = null
    @SerializedName(value = "hinhAnh", alternate = ["imageURL"])
    var imageURL: String? = null
    @SerializedName(value = "hangSanXuat", alternate = ["ten", "tenTaiSan"])
    var name: String? = null
    var ngayDenHan: Date? = null

    @SerializedName(value = "noLaiDenHan", alternate = ["laiDong"])
    var noLaiDenHan: Long? = null

    @SerializedName("moTa")
    var content: String? = null
    var tenNhom: String? = null
    var dongGiua: String? = null
        get() {
            if (ngayDenHan != null) {

                var time = Base.typeExpand.getDong1() + ngayDenHan?.toShow()
                if (noLaiDenHan != null) {
                    time = time + "\n" + "${Base.typeExpand.getDong2()}" + noLaiDenHan?.toPrice()
                }
                return time;

            }
            return tenNhom + "\n" + content
        }

    @SerializedName(value = "soTienVay", alternate = ["giaRaoBan"])
    var price: Long? = null
}