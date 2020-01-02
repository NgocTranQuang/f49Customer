package com.vn.f49kh.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vn.custom.util.PreferenceUtils
import com.vn.f49kh.R
import com.vn.f49kh.activity.main.MainActivity
import com.vn.f49kh.base.Base
import com.vn.f49kh.databinding.FragmentUserProfileBinding
import com.vn.f49kh.utils.GeneralUtils
import com.xxx.baseproject.base.BaseMVVMFragment
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.fragment_user_profile.*
import me.leolin.shortcutbadger.ShortcutBadger

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
        mViewModel?.isLogoutSuccess?.observe(this, Observer {
            if (it == true) {
                PreferenceUtils.writeBoolean(
                    activity
                        ?: return@Observer, PreferenceUtils.KEY_IS_LOGOUT, true
                )
                ShortcutBadger.removeCount(activity)
                (activity as MainActivity).selectTab(0);
            }
        })
    }

    fun logout() {
        var email = PreferenceUtils.getString(
            activity
                ?: return, PreferenceUtils.KEY_EMAIL, ""
        )
        var token = PreferenceUtils.getString(
            activity
                ?: return, PreferenceUtils.KEY_TOKEN_FIREBASE, ""
        )
        mViewModel?.pushTokenFirebase(email, token, GeneralUtils.getDeviceName(), false)
    }


}