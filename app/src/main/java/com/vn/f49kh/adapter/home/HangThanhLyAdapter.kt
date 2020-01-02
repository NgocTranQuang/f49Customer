package com.vn.f49kh.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowItemBinding
import com.vn.f49kh.databinding.RowThanhlyBinding
import com.vn.f49kh.model.taisan.TaiSanDTO
import extension.setOnSingleClickListener


class HangThanhLyAdapter(
    var items: MutableList<TaiSanDTO>,
    var clickItem: (TaiSanDTO?) -> Unit) :
    RecyclerView.Adapter<HangThanhLyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowThanhlyBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_thanhly, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<TaiSanDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowThanhlyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var item = items.getOrNull(position)
            item?.let {
                binding.item = it
                binding.executePendingBindings()
            }
            binding.root.setOnSingleClickListener {
                clickItem.invoke(item)
            }
        }
    }


}