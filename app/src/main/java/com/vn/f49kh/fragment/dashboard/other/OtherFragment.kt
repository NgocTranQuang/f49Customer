package com.vn.f49kh.fragment.dashboard.other

import com.vn.f49kh.R
import com.vn.f49kh.enumApp.DashboardTypeEnum
import com.vn.f49kh.fragment.dashboard.BaseDashBoardFragment
import com.vn.f49kh.model.home.ItemHomeDTO


class OtherFragment : BaseDashBoardFragment() {

    override fun getListCamdo(): MutableList<ItemHomeDTO> {
        var list = mutableListOf<ItemHomeDTO>()
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_baoquan
            this.title = DashboardTypeEnum.BAO_QUAN.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_cuahang
            this.title = DashboardTypeEnum.CUA_HANG.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_huongdan
            this.title = DashboardTypeEnum.HUONG_DAN.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_lienhe
            this.title = DashboardTypeEnum.LIEN_HE.toString()
        })
        return list
    }
}