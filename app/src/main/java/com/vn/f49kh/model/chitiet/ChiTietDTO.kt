package com.vn.f49kh.model.chitiet

import com.vn.f49kh.model.image.ImageDTO

class ChiTietDTO {
    var key: String? = null
    var value: String? = null

    var id: String? = null
    var tenVatCamCo: String? = null
    var giaThanhLy: Double? = null
    var noiDung: String? = null
    var listImage: MutableList<ImageDTO>? = null
}