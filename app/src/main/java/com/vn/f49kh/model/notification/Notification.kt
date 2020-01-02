package com.app.f49.model.notification

import com.google.gson.annotations.SerializedName
import java.util.*

class NotificationDTO {
    var id: Int? = null
    var itemId: Int? = null
    @SerializedName("hinhAnh")
    var imageURL: String? = null
    var tieuDe: String? = null
    var ngayGui: Date? = null
    var daDoc: Boolean? = false
    var screenId: String? = null
}

class NotificationVO {
    var title: String? = null
    var itemId: String? = null
    var screenId: String? = null
    var message: String? = null
}

class NotificationUnread {
    var countUnread: Int? = 0
}