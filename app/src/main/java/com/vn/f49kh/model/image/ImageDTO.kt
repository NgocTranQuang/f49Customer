package com.vn.f49kh.model.image

import com.google.gson.annotations.SerializedName
import java.util.*

class ImageDTO {
    @SerializedName(value = "url", alternate = ["imageURL", "bannerURL"])
    var url: String? = null
    var name: String? = null
    var extention: String? = null
    @SerializedName("eventURL")
    var linkWebView: String? = null
    var ngayBatDau: Date? = null
    var ngayKetThuc: Date? = null
    var moTa: String? = null
}