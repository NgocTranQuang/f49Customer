package com.vn.f49kh.model.taisan

import com.google.gson.annotations.SerializedName

class TaiSanTypeDTO {
    var id: String? = null
    @SerializedName("value")
    var name: String? = null
    @SerializedName(value="trangThai", alternate= ["tenNhom"])
    var trangThai: String? = null
    var showTextAll: Boolean? = false
    @SerializedName(value="listTaiSan", alternate= ["listDongLai"])
    var listTaiSan: MutableList<TaiSanDTO>? = null

}