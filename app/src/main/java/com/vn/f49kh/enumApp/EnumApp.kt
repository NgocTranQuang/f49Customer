package com.vn.f49kh.enumApp

import com.vn.f49kh.F49Application
import com.vn.f49kh.R

enum class EnumApp {
}

enum class DashboardTypeEnum(var isForceLogin: Boolean) {
    DINH_GIA(false) {
        override fun getNameEnum() = R.string.dinh_gia
    },
    CAM_DO(false) {
        override fun getNameEnum() = R.string.cam_do
    },
    DO_GIA_DUNG(false) {
        override fun getNameEnum() = R.string.do_gia_dung
    },
    TAI_SAN(true) {
        override fun getNameEnum() = R.string.tai_san
    },
    DONG_LAI(true) {
        override fun getNameEnum() = R.string.dong_lai
    },
    THANH_LY(false) {
        override fun getNameEnum() = R.string.thanh_ly
    },
    BAO_QUAN(false) {
        override fun getNameEnum() = R.string.bao_quan
    },
    CUA_HANG(false) {
        override fun getNameEnum() = R.string.cua_hang
    },
    HUONG_DAN(false) {
        override fun getNameEnum() = R.string.huong_dan
    },
    LIEN_HE(false) {
        override fun getNameEnum() = R.string.lien_he
    };

    companion object {
        private val map = DashboardTypeEnum.values().associateBy(DashboardTypeEnum::isForceLogin)
        fun get(type: Boolean?) = map[type]

    }

    override fun toString(): String {
        return F49Application.instance?.getString(getNameEnum()) ?: ""
    }

    abstract fun getNameEnum(): Int

}

enum class DinhGiaTaiSanTypeEnum() {
    OTO() {
        override fun getNameEnum() = R.string.oto
    },
    XE_MAY() {
        override fun getNameEnum() = R.string.xe_may
    },
    LAP_TOP() {
        override fun getNameEnum() = R.string.lap_top
    },
    DIEN_THOAI() {
        override fun getNameEnum() = R.string.dien_thoai
    },
    TABLET() {
        override fun getNameEnum() = R.string.tablet
    },
    TIVI() {
        override fun getNameEnum() = R.string.tivi
    },
    MAY_ANH() {
        override fun getNameEnum() = R.string.may_anh
    },
    SIM() {
        override fun getNameEnum() = R.string.sim
    },
    KHAC() {
        override fun getNameEnum() = R.string.khac
    };

    override fun toString(): String {
        return F49Application.instance?.getString(getNameEnum()) ?: ""
    }

    abstract fun getNameEnum(): Int

}