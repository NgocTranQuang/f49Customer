package com.vn.f49kh.model

import com.google.gson.annotations.SerializedName

class CuaHangDTO {
    @SerializedName("idCuaHang")
    var id: Int? = null
    @SerializedName(value = "imageURL",alternate = ["hinh"])
    var imageURL: String? = null
    var maCuaHang: String? = null
    @SerializedName("tenCuaHang")
    var name: String? = null
    @SerializedName("diaChi")
    var address: String? = null
    @SerializedName("dienThoai")
    var phoneNUmber: String? = null
    @SerializedName("gioLamViec")
    var openTime: String? = null

}