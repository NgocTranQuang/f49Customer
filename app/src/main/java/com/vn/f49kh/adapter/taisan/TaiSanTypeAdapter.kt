package com.vn.f49kh.adapter.taisan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowHeaderBinding
import com.vn.f49kh.extension.init
import com.vn.f49kh.model.taisan.TaiSanDTO
import com.vn.f49kh.model.taisan.TaiSanTypeDTO


class TaiSanTypeAdapter(
    var items: MutableList<TaiSanTypeDTO>,
    var clickItem: (TaiSanTypeDTO?) -> Unit,
    var clickItemChild: (TaiSanDTO?) -> Unit,
    var showArrowNext : Boolean
) :
    RecyclerView.Adapter<TaiSanTypeAdapter.ViewHolder>() {
    var heightItem = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowHeaderBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_header, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<TaiSanTypeDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var item = items.getOrNull(position)
            item?.let {
                binding.tvTitle.text = item.trangThai
                binding.rvTaiSan.init(space = R.dimen.toolbar_half_padding_horizontal)
                binding.rvTaiSan.adapter = TaiSanAdapter(it.listTaiSan ?: mutableListOf(), clickItemChild,showArrowNext)
                binding.executePendingBindings()
            }
        }
    }


}