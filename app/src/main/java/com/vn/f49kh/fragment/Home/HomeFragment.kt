package com.vn.f49kh.fragment.Home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vn.f49kh.R
import com.vn.f49kh.adapter.home.HomeAdapter
import com.vn.f49kh.binding.binImageUrl
import com.vn.f49kh.databinding.FragmentHomeBinding
import com.vn.f49kh.model.home.ItemHomeDTO
import com.vn.f49kh.utils.Utils
import com.xxx.baseproject.base.BaseMVVMFragment
import com.xxx.baseproject.customview.CustomGridLayoutManager
import com.xxx.baseproject.decoitem.DecoWithoutLeftRight
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseMVVMFragment<FragmentHomeBinding, HomeViewModel>() {
    var adapter: HomeAdapter? = null

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageList()
        setupRV(getListCamdo())
        initView()
        mViewModel?.getBannerImage()
    }

    private fun initView() {
        flHotLine.setOnSingleClickListener {
            Utils.callHotLine(activity!!)
        }
        mViewModel?.listImage?.observe(this, Observer {
            imgSlide.setListImage(
                it, 0
            )
        })
    }


    fun setImageList() {
        imgHome.binImageUrl("http://f49.vn/media/Images/index/img/img-gioithieu.jpg")
    }

    private fun setupRV(list: MutableList<ItemHomeDTO>) {
        adapter = HomeAdapter(list)
        var layoutManager = CustomGridLayoutManager(activity!!, 2)
        layoutManager.setScrollEnabled(false)
        rvItemHome.layoutManager = layoutManager
        rvItemHome.adapter = adapter
        rvItemHome.addItemDecoration(
            DecoWithoutLeftRight(
                context?.resources?.getDimensionPixelSize(R.dimen.height_line_size) ?: 1
            )
        )
        hideLoading()
    }

    fun getListCamdo(): MutableList<ItemHomeDTO> {
        var list = mutableListOf<ItemHomeDTO>()
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_oto
            this.title = getString(R.string.oto)
            this.id = "01"
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_xemay
            this.title = getString(R.string.xe_may)
            this.id = "02"
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_laptop
            this.title = getString(R.string.lap_top)
            this.id = "03"
        })
        list.add(ItemHomeDTO().apply {
            this.imageInt = R.drawable.ic_dashboard_mayanh
            this.title = getString(R.string.may_anh)
            this.id = "07"
        })
        return list
    }

//    override fun showLoading() {
//        shimmer.visibility = View.VISIBLE
//    }
//
//    override fun hideLoading() {
//        shimmer.visibility = View.GONE
//    }
}