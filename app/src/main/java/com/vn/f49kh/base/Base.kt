package com.vn.f49kh.base

import com.app.f49.model.login.LoginDTO
import com.vn.f49kh.enumApp.ExpandType

object Base {
    var pageSize = 50
    var username: LoginDTO? = null
    var typeExpand: ExpandType = ExpandType.TAI_SAN
}