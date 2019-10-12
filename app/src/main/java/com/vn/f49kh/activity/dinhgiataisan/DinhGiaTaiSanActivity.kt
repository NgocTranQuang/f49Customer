package com.vn.f49kh.activity.dinhgiataisan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.app.f49.bottomsheet.imageaction.BottomSheetGetImageFragment
import com.vn.f49kh.R
import com.vn.f49kh.adapter.dinhgiataisan.DinhGiaTaiSanAdapter
import com.vn.f49kh.adapter.dinhgiataisan.UploadImageAdapter
import com.vn.f49kh.databinding.ActivityDinhgiataisanBinding
import com.vn.f49kh.deco.CategoryDecoration
import com.vn.f49kh.enumApp.DashboardTypeEnum
import com.vn.f49kh.enumApp.DinhGiaTaiSanTypeEnum
import com.vn.f49kh.model.dinhgiataisan.UploadImageDTO
import com.vn.f49kh.model.home.ItemHomeDTO
import com.xxx.baseproject.base.BaseMVVMActivity
import com.xxx.baseproject.customview.CustomGridLayoutManager
import com.xxx.baseproject.decoitem.RVTowColumnDecoration
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_dinhgiataisan.*

class DinhGiaTaiSanActivity : BaseMVVMActivity<ActivityDinhgiataisanBinding, DinhGiaTaiSanViewModel>() {
    var countOfImage: Int = 0
    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, DinhGiaTaiSanActivity::class.java))
        }
    }

    var adapter: DinhGiaTaiSanAdapter? = null
    var adapterImage = UploadImageAdapter(mutableListOf())

    override fun getLayout(): Int {
        return R.layout.activity_dinhgiataisan
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRV()
        initRVImage()
        setonClick()
    }


    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.dinh_gia_tai_san)
    }

    private fun setUpRV() {
        rvDinhGia?.post {
            adapter = DinhGiaTaiSanAdapter(getListRV(), rvDinhGia.measuredHeight, ::clickItem)
            var layoutManager = CustomGridLayoutManager(this, 3)
            layoutManager.setScrollEnabled(false)
            rvDinhGia.layoutManager = layoutManager
            rvDinhGia.adapter = adapter
            rvDinhGia.addItemDecoration(
                RVTowColumnDecoration(
                    resources?.getDimensionPixelSize(R.dimen.activity_horizontal_margin) ?: 24
                )
            )
        }
    }
    private fun initRVImage() {

        var layoutManager = CustomGridLayoutManager(this, 4)
        layoutManager.setScrollEnabled(false)
        rvImage.layoutManager = layoutManager
        rvImage.adapter = adapterImage
        rvImage.addItemDecoration(
            CategoryDecoration(
                resources?.getDimensionPixelSize(R.dimen.toolbar_half_padding_horizontal) ?: 8
            )
        )
    }
    private fun getListRV(): MutableList<ItemHomeDTO> {
        var list = mutableListOf<ItemHomeDTO>()
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_oto
            this.title = DinhGiaTaiSanTypeEnum.OTO.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_xemay
            this.title = DinhGiaTaiSanTypeEnum.XE_MAY.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_laptop
            this.title = DinhGiaTaiSanTypeEnum.LAP_TOP.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_dienthoai
            this.title = DinhGiaTaiSanTypeEnum.DIEN_THOAI.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_tablet
            this.title = DinhGiaTaiSanTypeEnum.TABLET.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_tivi
            this.title = DinhGiaTaiSanTypeEnum.TIVI.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_mayanh
            this.title = DinhGiaTaiSanTypeEnum.MAY_ANH.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_sim
            this.title = DinhGiaTaiSanTypeEnum.SIM.toString()
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dinhgia_khac
            this.title = DinhGiaTaiSanTypeEnum.KHAC.toString()
        })
        return list
    }

    open fun clickItem(itemHomeDTO: ItemHomeDTO) {
        when (itemHomeDTO.title) {
            DashboardTypeEnum.DINH_GIA.toString() -> {

            }
            DashboardTypeEnum.CAM_DO.toString() -> {

            }
            DashboardTypeEnum.DO_GIA_DUNG.toString() -> {

            }
            DashboardTypeEnum.TAI_SAN.toString() -> {

            }
            DashboardTypeEnum.DONG_LAI.toString() -> {

            }
            DashboardTypeEnum.THANH_LY.toString() -> {

            }
            DashboardTypeEnum.BAO_QUAN.toString() -> {

            }
            DashboardTypeEnum.CUA_HANG.toString() -> {

            }
            DashboardTypeEnum.HUONG_DAN.toString() -> {

            }
            DashboardTypeEnum.LIEN_HE.toString() -> {

            }

        }
    }
    private fun setonClick() {
        lnUploadImage.setOnSingleClickListener {
            BottomSheetGetImageFragment.show(supportFragmentManager, BottomSheetGetImageFragment.TypePickImage.MULTI_PICK) { listImage, listBase64 ->
                if ((countOfImage + adapterImage?.items?.size + listImage.size) > 10) {
                    showDialog(getString(R.string.max_image))
                    return@show
                }
                var listImageUpload = mutableListOf<UploadImageDTO>()
                listImage.forEachIndexed { index, s ->
                    var uploadImage = UploadImageDTO()
                    uploadImage.uri = s
//                    uploadImage.soHopDong = soHopDong
                    uploadImage.imageBase64 = listBase64.get(index)
                    listImageUpload.add(uploadImage)
                }
                adapterImage?.insertData(listImageUpload)
            }
        }
//        btnDone.setOnSingleClickListener {
//            mViewModel?.listImageUpload = adapter.items
//            mViewModel?.uploadImage()
//        }
//        btnTuChoi.setOnSingleClickListener {
//            finish()
//        }
    }
}