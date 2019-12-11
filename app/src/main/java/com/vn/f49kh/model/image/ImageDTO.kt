package com.vn.f49kh.model.image

import com.google.gson.annotations.SerializedName

class ImageDTO {
    @SerializedName(value = "url", alternate = ["imageURL"])
    var url: String? = null
    var name: String? = null
    var extention: String? = null

}