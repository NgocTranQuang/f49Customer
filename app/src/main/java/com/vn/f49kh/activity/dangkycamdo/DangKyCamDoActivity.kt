package com.vn.f49kh.activity.dangkycamdo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vn.f49kh.R
import com.vn.f49kh.databinding.ActivityDangkyCamdoBinding
import com.vn.f49kh.extension.addCurrencyFormatter
import com.vn.f49kh.extension.isPhoneNumber
import com.xxx.baseproject.base.BaseMVVMActivity
import extension.selectedItemListener
import extension.setList
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_dangky_camdo.*

class DangKyCamDoActivity : BaseMVVMActivity<ActivityDangkyCamdoBinding, DangKyCamDoViewModel>() {
    var asset: String? = null
    var id: String? = null

    companion object {
        val KEY_DATA = "ID"
        fun start(context: Context?, id: String?) {
            context?.startActivity(Intent(context, DangKyCamDoActivity::class.java).putExtra(KEY_DATA, id))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_dangky_camdo
    }

    override fun getMyToolbar(): Toolbar? {
        return tb
    }

    override fun getTitleToolbar(): String? {
        return getString(R.string.dang_ky_cam_do)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        id = intent?.getStringExtra(KEY_DATA)
        spTaiSan.selectedItemListener {
            asset = mViewModel?.listLoaiTaiSan?.value?.get(it)?.name
            mViewModel?.getDanhMucSanPham(mViewModel?.listLoaiTaiSan?.value?.get(it)?.id ?: "")
        }
        lnBottom.setOnSingleClickListener {
            if (edtBalance.text.toString().isNullOrEmpty() || edtFullName.text.toString().isNullOrEmpty() || edtBrad.text.toString().isNullOrEmpty() || !edtPhoneNumber.isPhoneNumber() || edtDes.text.toString().isNullOrEmpty()) {
                showDialog(getString(R.string.invalid_data))
                return@setOnSingleClickListener
            }
            mViewModel?.putDKCD(
                edtBalance.text.toString().replace(".","").toDoubleOrNull() ?: 0.0,
                edtFullName.text.toString(),
                asset ?: "",
                edtBrad.text.toString(),
                edtPhoneNumber.text.toString(),
                edtDes.text.toString()
            )
        }
        edtBalance.addCurrencyFormatter()
        mViewModel?.getLoaiTaiSan()
        mViewModel?.listLoaiTaiSan?.observe(this, Observer {
            progress_bar.visibility = View.GONE
            var selectedP = 0
            for (i in it.indices) {
                var item = it.get(i)
                if (item.id == id) {
                    selectedP = i;
                    break
                }
            }
            spTaiSan.setList(it.map { it.name }?.toMutableList(), selectedP)
        })
        mViewModel?.listDanhMuc?.observe(this, Observer {
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, it)
            edtBrad.setAdapter<ArrayAdapter<String>>(adapter)
        })
    }

}