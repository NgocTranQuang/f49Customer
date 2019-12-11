package com.vn.f49kh.model.chitiet

import com.vn.f49kh.model.image.ImageDTO
import java.util.*

class ChiTietDongLaiDTO {

    var ten: String? = null
    var listImage: MutableList<ImageDTO>? = null
    var trangThai: String? = null
    var soHD: String? = null
    var ngayHieuLuc: Date? = null
    var ngayDenHan: Date? = null
    var noGocDenHan: Long? = null
    var noLaiDenHan: Long? = null
}