package com.vn.f49kh.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vn.f49kh.R
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.change_password_bottom_sheet.*

class ChangePasswordBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun show(fm: FragmentManager?) {
            fm?.let {
                val bottomSheetFragment = ChangePasswordBottomSheet()
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

        }
        cvDongY.setOnSingleClickListener {

        }
    }
}