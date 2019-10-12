package com.vn.f49kh.fragment.profile

import android.os.Bundle
import android.view.View
import com.vn.custom.activity.base.BaseViewModel
import com.vn.f49kh.R
import com.vn.f49kh.databinding.FragmentUserProfileBinding
import com.xxx.baseproject.base.BaseMVVMFragment
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.fragment_user_profile.*

class ProfileUserFragment : BaseMVVMFragment<FragmentUserProfileBinding, BaseViewModel>() {
    override fun getLayout(): Int {
        return R.layout.fragment_user_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclick()
    }

    private fun setOnclick() {
        lnChangePassword.setOnSingleClickListener {
            ChangePasswordBottomSheet.show(fragmentManager)
        }
    }
}