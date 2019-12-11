package com.vn.f49kh.activity.dinhgiataisan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.app.f49.bottomsheet.imageaction.BottomSheetGetImageFragment
import com.vn.f49kh.R
import com.vn.f49kh.adapter.dinhgiataisan.DinhGiaTaiSanAdapter
import com.vn.f49kh.adapter.dinhgiataisan.UploadImageAdapter
import com.vn.f49kh.databinding.ActivityDinhgiataisanBinding
import com.vn.f49kh.deco.CategoryDecoration
import com.vn.f49kh.enumApp.DinhGiaTaiSanTypeEnum
import com.vn.f49kh.extension.isPhoneNumber
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
        var id = ""
        when (itemHomeDTO.title) {
            DinhGiaTaiSanTypeEnum.OTO.toString() -> {
                id = "01"
            }
            DinhGiaTaiSanTypeEnum.XE_MAY.toString() -> {
                id = "02"
            }
            DinhGiaTaiSanTypeEnum.LAP_TOP.toString() -> {
                id = "03"
            }
            DinhGiaTaiSanTypeEnum.DIEN_THOAI.toString() -> {
                id = "04"
            }
            DinhGiaTaiSanTypeEnum.TABLET.toString() -> {
                id = "05"
            }
            DinhGiaTaiSanTypeEnum.TIVI.toString() -> {
                id = "06"
            }
            DinhGiaTaiSanTypeEnum.MAY_ANH.toString() -> {
                id = "07"
            }
            DinhGiaTaiSanTypeEnum.SIM.toString() -> {
                id = "08"
            }
            DinhGiaTaiSanTypeEnum.KHAC.toString() -> {
                id = ""
            }

        }
        mViewModel?.getDanhMucSanPham(id)
    }

    private fun setonClick() {
        lnUploadImage.setOnSingleClickListener {
            BottomSheetGetImageFragment.show(
                supportFragmentManager,
                BottomSheetGetImageFragment.TypePickImage.SINGLE_PICK
            ) { listImage, listBase64 ->
                if ((countOfImage + adapterImage?.items?.size + listImage.size) > 1) {
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
        lnBottom.setOnSingleClickListener {
            if (edtInfoProduct.text.toString().isNullOrEmpty() || edtFullName.text.toString().isNullOrEmpty() || !edtPhoneNumber.isPhoneNumber()) {
                showDialog(getString(R.string.invalid_data))
                return@setOnSingleClickListener
            }
            mViewModel?.listImageUpload = adapterImage.items
            mViewModel?.uploadImage()
        }
    }

    override fun observer() {
        super.observer()
        mViewModel?.currentIndex?.observe(this, Observer {
            it?.let {
                if (it >= 0) {
//                    showDialogWithMessage("Đang up ảnh thứ ${it + 1}/${adapter?.items?.size}")
                    showDialogWithMessage("Đang up ảnh. Xin vui lòng chờ.")
                }
            }
        })
        mViewModel?.finishedUploading?.observe(this, Observer
        {
            hideDialogWithMesasge()
            if (it == true) {
                mViewModel?.guiDinhGiaTaiSan(
                    edtInfoProduct.text.toString(),
                    edtFullName.text.toString(),
                    edtPhoneNumber.text.toString(),
                    mViewModel?.linkImage ?: ""
                )
//                showDialog(getString(R.string.upload_thanhcong)) {
////                    var intent = Intent()
////                    intent.putExtra(KEY_PASS_DATA, true)
////                    setResult(RESULT_OK, intent);
////                    finish()
////                }
            } else {
                showDialog(getString(R.string.upload_fail))
            }
        })
        mViewModel?.listDanhMuc?.observe(this, Observer {
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, it)
            edtInfoProduct.setAdapter<ArrayAdapter<String>>(adapter)
        })
    }
}