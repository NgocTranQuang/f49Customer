package com.vn.f49kh.activity.lienhe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.vn.f49kh.R
import com.vn.f49kh.activity.BaseF49Activity
import com.vn.f49kh.databinding.ActivityLienheBinding
import com.vn.f49kh.extension.isEmailAddress
import com.vn.f49kh.extension.isPhoneNumber
import extension.selectedItemListener
import extension.setList
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_lienhe.*

class LienHeActivity : BaseF49Activity<ActivityLienheBinding, LienHeViewModel>() {

    var provinceChoose = ""

    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, LienHeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView() {
        spProvince.setList(listProvinces, 0)
        spProvince?.selectedItemListener {
            provinceChoose = listProvinces?.get(it) ?: ""
        }
        lnBottom.setOnSingleClickListener {
            if (edtFullName.text.toString().isNullOrEmpty() || !edtPhoneNumber.isPhoneNumber() || !edtEmail.isEmailAddress() || edtContent.text.toString().isNullOrEmpty()) {
                showDialog(getString(R.string.invalid_data))
                return@setOnSingleClickListener
            }
            mViewModel?.putLienHe(
                edtFullName.text.toString(),
                edtPhoneNumber.text.toString(),
                provinceChoose,
                edtEmail.text.toString(),
                edtContent.text.toString()
            )
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_lienhe
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.lien_he)
    }

    var listProvinces: MutableList<String?>? = mutableListOf(
        "An Giang",
        "Bà Rịa-Vũng Tàu",
        "Bạc Liêu",
        "Bắc Kạn",
        "Bắc Giang",
        "Bắc Ninh",
        "Bến Tre",
        "Bình Dương",
        "Bình Định",
        "Bình Phước",
        "Bình Thuận",
        "Cà Mau",
        "Cao Bằng",
        "Cần Thơ (TP)",
        "Đà Nẵng (TP)",
        "Đắk Lắk",
        "Đắk Nông",
        "Điện Biên",
        "Đồng Nai",
        "Đồng Tháp",
        "Gia Lai",
        "Hà Giang",
        "Hà Nam",
        "Hà Nội (TP)",
        "Hà Tây",
        "Hà Tĩnh",
        "Hải Dương",
        "Hải Phòng (TP)",
        "Hòa Bình",
        "Hồ Chí Minh (TP)",
        "Hậu Giang",
        "Hưng Yên",
        "Khánh Hòa",
        "Kiên Giang",
        "Kon Tum",
        "Lai Châu",
        "Lào Cai",
        "Lạng Sơn",
        "Lâm Đồng",
        "Long An",
        "Nam Định",
        "Nghệ An",
        "Ninh Bình",
        "Ninh Thuận",
        "Phú Thọ",
        "Phú Yên",
        "Quảng Bình",
        "Quảng Nam",
        "Quảng Ngãi",
        "Quảng Ninh",
        "Quảng Trị",
        "Sóc Trăng",
        "Sơn La",
        "Tây Ninh",
        "Thái Bình",
        "Thái Nguyên",
        "Thanh Hóa",
        "Thừa Thiên – Huế",
        "Tiền Giang",
        "Trà Vinh",
        "Tuyên Quang",
        "Vĩnh Long",
        "Vĩnh Phúc",
        "Yên Bái"
    )
}