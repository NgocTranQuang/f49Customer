package com.vn.f49kh.model.dinhgiataisan

import com.google.gson.annotations.SerializedName

class UploadImageDTO {
    var uri: String? = null
    @SerializedName("imgStr")
    var imageBase64: String? = null
}