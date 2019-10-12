package com.vn.f49kh.fragment.dashboard.service

import com.vn.f49kh.R
import com.vn.f49kh.enumApp.DashboardTypeEnum
import com.vn.f49kh.fragment.dashboard.BaseDashBoardFragment
import com.vn.f49kh.model.home.ItemHomeDTO


class ServiceFragment : BaseDashBoardFragment() {

    override fun getListCamdo(): MutableList<ItemHomeDTO> {
        var list = mutableListOf<ItemHomeDTO>()
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_dinhgia
            this.title = DashboardTypeEnum.DINH_GIA.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_camdo
            this.title = DashboardTypeEnum.CAM_DO.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_dogiadung
            this.title = DashboardTypeEnum.DO_GIA_DUNG.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_taisan
            this.title = DashboardTypeEnum.TAI_SAN.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_donglai
            this.title = DashboardTypeEnum.DONG_LAI.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_thanhly
            this.title = DashboardTypeEnum.THANH_LY.toString()
        })
        return list
    }
}