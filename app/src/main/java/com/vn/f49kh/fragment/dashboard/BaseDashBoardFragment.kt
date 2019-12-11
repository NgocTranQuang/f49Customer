package com.vn.f49kh.fragment.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vn.f49kh.R
import com.vn.f49kh.activity.baoquan.BaoQuanActivity
import com.vn.f49kh.activity.camdogiadung.CamdogiadungActivity
import com.vn.f49kh.activity.cuahang.CuaHangActivity
import com.vn.f49kh.activity.dangkycamdo.DangKyCamDoActivity
import com.vn.f49kh.activity.dinhgiataisan.DinhGiaTaiSanActivity
import com.vn.f49kh.activity.donglai.DongLaiActivity
import com.vn.f49kh.activity.huongdan.HuongDanActivity
import com.vn.f49kh.activity.lienhe.LienHeActivity
import com.vn.f49kh.activity.taisan.TaiSanActivity
import com.vn.f49kh.activity.thanhly.ThanhLyActivity
import com.vn.f49kh.adapter.service.ServiceAdapter
import com.vn.f49kh.databinding.FragmentSeviceBinding
import com.vn.f49kh.enumApp.DashboardTypeEnum
import com.vn.f49kh.extension.checkLogin
import com.vn.f49kh.model.home.ItemHomeDTO
import com.xxx.baseproject.base.BaseMVVMFragment
import com.xxx.baseproject.customview.CustomGridLayoutManager
import com.xxx.baseproject.decoitem.RVTowColumnDecoration
import kotlinx.android.synthetic.main.fragment_sevice.*


open class BaseDashBoardFragment : BaseMVVMFragment<FragmentSeviceBinding, BaseDashboardViewModel>() {
    override fun getLayout(): Int {
        return R.layout.fragment_sevice
    }

    companion object {
        val KEY_DATA = "KEY_DATA"
        fun newInstance(): BaseDashBoardFragment {
            var fg = BaseDashBoardFragment()
            return fg
        }
    }

    var adapter: ServiceAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV(getListCamdo())
    }

    override fun getViewModelClass(): Class<BaseDashboardViewModel> {
        return BaseDashboardViewModel::class.java
    }

    private fun setupRV(list: MutableList<ItemHomeDTO>) {
        rvService?.post {
            adapter = ServiceAdapter(list, rvService.measuredHeight, ::clickItem)
            var layoutManager = CustomGridLayoutManager(activity!!, 2)
            layoutManager.setScrollEnabled(false)
            rvService.layoutManager = layoutManager
            rvService.adapter = adapter
            rvService.addItemDecoration(
                RVTowColumnDecoration(
                    context?.resources?.getDimensionPixelSize(R.dimen.activity_horizontal_margin) ?: 24
                )
            )
        }
    }

    open fun clickItem(itemHomeDTO: ItemHomeDTO) {
        when (itemHomeDTO.title) {
            DashboardTypeEnum.DINH_GIA.toString() -> {
                DinhGiaTaiSanActivity.start(activity)
            }
            DashboardTypeEnum.CAM_DO.toString() -> {
                DangKyCamDoActivity.start(activity,null)
            }
            DashboardTypeEnum.DO_GIA_DUNG.toString() -> {
                CamdogiadungActivity.start(activity)
            }
            DashboardTypeEnum.TAI_SAN.toString() -> {
                (activity as AppCompatActivity).checkLogin {
                    TaiSanActivity.start(activity)
                }
            }
            DashboardTypeEnum.DONG_LAI.toString() -> {
                (activity as AppCompatActivity).checkLogin {
                    DongLaiActivity.start(activity)
                }
            }
            DashboardTypeEnum.THANH_LY.toString() -> {
                ThanhLyActivity.start(activity)
            }
            DashboardTypeEnum.BAO_QUAN.toString() -> {
                BaoQuanActivity.start(activity)
            }
            DashboardTypeEnum.CUA_HANG.toString() -> {
                CuaHangActivity.start(activity)
            }
            DashboardTypeEnum.HUONG_DAN.toString() -> {
                HuongDanActivity.start(activity)
            }
            DashboardTypeEnum.LIEN_HE.toString() -> {
                LienHeActivity.start(activity)
            }

        }
    }

    open fun getListCamdo(): MutableList<ItemHomeDTO> {
        var list = mutableListOf<ItemHomeDTO>()
        return list
    }
}