package com.vn.f49kh.fragment.Home

import android.os.Bundle
import android.view.View
import com.stfalcon.frescoimageviewer.ImageViewer
import com.vn.f49kh.R
import com.vn.f49kh.adapter.home.HomeAdapter
import com.vn.f49kh.binding.binImageUrl
import com.vn.f49kh.databinding.FragmentHomeBinding
import com.vn.f49kh.model.home.ItemHomeDTO
import com.vn.f49kh.utils.Constant
import com.xxx.baseproject.base.BaseMVVMFragment
import com.xxx.baseproject.customview.CustomGridLayoutManager
import com.xxx.baseproject.decoitem.DecoWithoutLeftRight
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseMVVMFragment<FragmentHomeBinding, HomeViewModel>() {
    var adapter: HomeAdapter? = null
    var listImage = mutableListOf(
        Constant.IMAGE_URL_DEFAULT,
        Constant.IMAGE_URL_DEFAULT,
        Constant.IMAGE_URL_DEFAULT,
        Constant.IMAGE_URL_DEFAULT
    )

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageList()
        setupRV(getListCamdo())
    }

    fun setImageList() {
        imgSlide.setListImage(
            listImage, 0
        )

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
            this.imageURL = "http://img.vietnamfinance.vn/upload/news/ledinhnga/2018/7/17/hondabrio-banxehoico-02f7.jpg"
            this.title = "oto"
        })
        list.add(ItemHomeDTO().apply {
            this.imageURL = "http://img.vietnamfinance.vn/upload/news/ledinhnga/2018/7/17/hondabrio-banxehoico-02f7.jpg"
            this.title = "xem may"
        })
        list.add(ItemHomeDTO().apply {
            this.imageURL = "http://img.vietnamfinance.vn/upload/news/ledinhnga/2018/7/17/hondabrio-banxehoico-02f7.jpg"
            this.title = "lap top"
        })
        list.add(ItemHomeDTO().apply {
            this.imageURL = "http://img.vietnamfinance.vn/upload/news/ledinhnga/2018/7/17/hondabrio-banxehoico-02f7.jpg"
            this.title = "may anh"
        })
        return list
    }

    override fun showLoading() {
        shimmer.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        shimmer.visibility = View.GONE
    }
}