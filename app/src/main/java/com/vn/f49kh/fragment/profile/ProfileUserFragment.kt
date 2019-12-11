package com.vn.f49kh.fragment.profile

import android.os.Bundle
import android.view.View
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.main.MainActivity
import com.vn.f49kh.base.Base
import com.vn.f49kh.databinding.FragmentUserProfileBinding
import com.xxx.baseproject.base.BaseMVVMFragment
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.fragment_user_profile.*

class ProfileUserFragment : BaseMVVMFragment<FragmentUserProfileBinding, UserProfileViewModel>() {
    override fun getLayout(): Int {
        return R.layout.fragment_user_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclick()
        mViewBinding?.data = Base
    }

    private fun setOnclick() {
        lnChangePassword.setOnSingleClickListener {
            ChangePasswordBottomSheet.show(fragmentManager) { currentPassword, newPassword ->
                mViewModel?.changePassword(currentPassword, newPassword)
            }
        }
        lnLogout.setOnSingleClickListener {
            logout()
        }
    }

    fun logout() {
        PreferenceUtils.writeBoolean(
            activity
                ?: return, PreferenceUtils.KEY_IS_LOGOUT, true
        )
        (activity as MainActivity).selectTab(0);

    }


}