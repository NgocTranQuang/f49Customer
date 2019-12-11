package com.vn.f49kh.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vn.f49kh.R
import com.vn.f49kh.activity.main.MainActivity
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.change_password_bottom_sheet.*

class ChangePasswordBottomSheet : BottomSheetDialogFragment() {
    var changePassword: ((String, String) -> Unit)? = null

    companion object {
        fun show(fm: FragmentManager?, changePass: ((String, String) -> Unit)?) {
            fm?.let {
                val bottomSheetFragment = ChangePasswordBottomSheet()
                bottomSheetFragment.changePassword = changePass
                bottomSheetFragment.show(fm, bottomSheetFragment.tag)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.change_password_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventClick()
    }

    private fun setEventClick() {
        cvTuChoi.setOnSingleClickListener {
            dismiss()
        }
        cvDongY.setOnSingleClickListener {
            if (edt_password.text.toString().isNullOrEmpty() || edt_new_password.text.toString().isNullOrEmpty() || edt_new_password_again.text.toString().isNullOrEmpty()) {
                (activity as MainActivity).showDialog("Xin vui lòng nhập đầy đủ thông tin.")
            } else if (!edt_new_password.text.toString().equals(edt_new_password_again.text.toString())) {
                (activity as MainActivity).showDialog("Mật khẩu mới và xác nhận mật khẩu mới không trùng nhau. Xin vui lòng kiểm tra lại.")
            } else {
                changePassword?.invoke(edt_password.text.toString(), edt_new_password.text.toString())
                dismiss()
            }
        }
    }
}